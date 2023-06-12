package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPauseUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getExoPlayerUseCase: GetExoPlayerUseCase,
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaPauseUseCase: GetMediaPauseUseCase,
    private val getMediaPrepareUseCase: GetMediaPrepareUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
) : ViewModel() {
    private val _UiIsPlaying = MutableLiveData<Boolean>(false)
    val UiIsPlying: LiveData<Boolean> = _UiIsPlaying
    private fun updateServiceIsPlaying() {
        viewModelScope.launch {
            _UiIsPlaying.value =  getExoPlayerUseCase().isPlaying
            Log.i("GusMorMV Usecase", getExoPlayerUseCase().isPlaying.toString())
            Log.i("GusMorMV", _UiIsPlaying.value.toString())

        }

    }

    fun prepare() {
        viewModelScope.launch {
            getMediaPrepareUseCase()
        }
    }

    fun addMediaItem(uri: Uri) {
        viewModelScope.launch {
            getMediaAddItemUseCase(uri)
        }
    }

    fun play() {
        viewModelScope.launch {
            getMediaPlayUseCase()
            while (UiIsPlying.value != true)  {
                delay(100)
                updateServiceIsPlaying()
            }
        }
    }

    fun pause() {
        viewModelScope.launch {
            getMediaPauseUseCase()
        }.apply {
            updateServiceIsPlaying()
        }
    }

    fun stop() {
        viewModelScope.launch {
            getMediaStopUseCase()
        }.apply {
            updateServiceIsPlaying()
        }
    }
}
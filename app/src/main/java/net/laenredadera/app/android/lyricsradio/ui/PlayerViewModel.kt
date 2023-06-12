package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPauseUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaQueryIsPlayingUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getMediaQueryIsPlayingUseCase: GetMediaQueryIsPlayingUseCase,
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaPauseUseCase: GetMediaPauseUseCase,
    private val getMediaPrepareUseCase: GetMediaPrepareUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
) : ViewModel() {

    private val _uiIsPlaying = MutableLiveData(false)
    val uiIsPlying: LiveData<Boolean> = _uiIsPlaying


    private fun updateServiceIsPlaying() {
        viewModelScope.launch {
            _uiIsPlaying.value = getMediaQueryIsPlayingUseCase()
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
            while (uiIsPlying.value != true) {
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
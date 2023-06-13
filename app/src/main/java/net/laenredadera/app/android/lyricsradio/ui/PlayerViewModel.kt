package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPauseUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaQueryIsPlayingUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationDataUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getMediaQueryIsPlayingUseCase: GetMediaQueryIsPlayingUseCase,
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaPauseUseCase: GetMediaPauseUseCase,
    private val getMediaPrepareUseCase: GetMediaPrepareUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
    private val getStationDataUseCase: GetStationDataUseCase,
) : ViewModel() {

    var newItem: Boolean = true
    private var _station: RadioStationModel? = null
    var station = MutableLiveData<String>("")

    private val _song = MutableStateFlow<List<String?>>(emptyList())
    var song: StateFlow<List<String?>> = _song.asStateFlow()

    private val _uiIsPlaying = MutableLiveData(false)
    val uiIsPlying: LiveData<Boolean> = _uiIsPlaying

    init {
        viewModelScope.launch {
            _song.value = listOf(" "," ")

            while ( true) {
                  delay(1000)
                if (!newItem ) {
                    _song.value = getStationDataUseCase()
                }
            }
        }
    }
    private fun updateServiceIsPlaying() {
        viewModelScope.launch {
            _uiIsPlaying.value = getMediaQueryIsPlayingUseCase()
            newItem = false

        }
    }

    fun prepare() {
        viewModelScope.launch {
            _song.value = listOf(" "," ")
            getMediaPrepareUseCase()
        }
    }

    fun addMediaItem(uri: Uri) {
        stop()
        _song.value = listOf(" "," ")
        newItem = true;
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
        }.apply{

            updateServiceIsPlaying()
        }
    }
    fun addStationModel(radioStationModel: RadioStationModel) {
        _station = radioStationModel
    }
}
package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumCoverUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPauseUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaQueryIsPlayingUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaSetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationAddOnePlayedUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationNumberOfTimesPlayedUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationDataUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getExoPlayerUseCase: GetExoPlayerUseCase,
    private val getMediaQueryIsPlayingUseCase: GetMediaQueryIsPlayingUseCase,
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaPauseUseCase: GetMediaPauseUseCase,
    private val getMediaPrepareUseCase: GetMediaPrepareUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
    private val getStationDataUseCase: GetStationDataUseCase,
    private val getMediaSetVolumeUseCase: GetMediaSetVolumeUseCase,
    private val getAlbumCoverUseCase: GetAlbumCoverUseCase,
    private val getRadioStationAddOnePlayedUseCase: GetRadioStationAddOnePlayedUseCase,
    private val getRadioStationNumberOfTimesPlayedUseCase: GetRadioStationNumberOfTimesPlayedUseCase
) : ViewModel() {

    private var _cover = MutableLiveData("")
    var cover: LiveData<String> = _cover

    private var _station: RadioStationModelUI? = null
    var station = MutableLiveData<RadioStationModelUI?>()

    private val _song = MutableStateFlow<List<String?>>(listOf(" ", " "))
    var song: StateFlow<List<String?>> = _song.asStateFlow()

    private val _uiIsPlaying = MutableStateFlow(false)
    val uiIsPlaying: StateFlow<Boolean> = _uiIsPlaying.asStateFlow()

    private val _uiIsPaused = MutableStateFlow(false)
    val uiIsPaused: StateFlow<Boolean> = _uiIsPaused.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                delay(100)
                if (_uiIsPlaying.value!!) {
                    _song.value = getStationDataUseCase()
                } else
                    station.value = _station
                addListener()
            }
            albumCover()
        }
    }

    private fun addListener() {
        getExoPlayerUseCase().addListener(
            object : Player.Listener {
                override fun onMediaItemTransition(
                    mediaItem: MediaItem?,
                    reason: Int
                ) {
                    _song.value = listOf(" ", " ")
                }
            }
        )
    }

    private fun updateServiceIsPlaying() {
        viewModelScope.launch {
            _uiIsPlaying.value = getMediaQueryIsPlayingUseCase()
        }
    }

    fun prepare() {
        viewModelScope.launch {
            getMediaPrepareUseCase()
        }.apply {
            addListener()
        }
    }

    suspend fun addMediaItem(uri: Uri) {
        stop()
        viewModelScope.launch {
            getMediaAddItemUseCase(uri)
        }
    }

    fun addStationModel(radioStationModel: RadioStationModelUI) {
        viewModelScope.launch {
            _station = radioStationModel
        }
    }

    suspend fun play() {
        viewModelScope.launch {
            getMediaPlayUseCase()
            while (!_uiIsPlaying.value) {
                delay(100)
                _uiIsPlaying.value = true
                _uiIsPaused.value = false
            }
        }.apply {
            addOnePlayedTime()
        }
    }

    fun pause() {
        viewModelScope.launch {
            getMediaPauseUseCase()
            _uiIsPlaying.value = true
            _uiIsPaused.value = true
        }
    }

    fun stop() {
        viewModelScope.launch {
            getMediaStopUseCase()
            _uiIsPlaying.value = false
            _uiIsPaused.value = true
        }.apply {
            _song.value = listOf(" ", " ")
        }
    }

    fun setVolume(vol: Float) {
        viewModelScope.launch {
            getMediaSetVolumeUseCase(vol)
        }
    }

    fun getTrackInfo() {
        viewModelScope.launch {
            try {
                _song.value = getStationDataUseCase()
            } catch (e: Exception) {
            }
        }
    }

    fun albumCover() {
        viewModelScope.launch {
            try {
                while (true) {
                    delay(3000)
                    if (_uiIsPlaying.value) {
                        _cover.value =
                            getAlbumCoverUseCase(_song.value[0] ?: "", _song.value[1] ?: "")
                    } else {
                        delay(1000)
                        _cover.value = ""
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    private fun addOnePlayedTime() {
        viewModelScope.launch {
            try {
                getRadioStationAddOnePlayedUseCase(_station!!.id)
                var num = getRadioStationNumberOfTimesPlayedUseCase(_station!!.id)
                Log.i("GusMor station id: ", _station!!.id.toString())
                Log.i("GusMor station id played: ", num.toString())
            } catch (e: Exception) {
                Log.i("GusMor station id: ", e.toString())
            }
        }
    }

}
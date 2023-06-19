package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
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
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPauseUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaQueryIsPlayingUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaSetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationDataUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
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
    private val getMediaSetVolumeUseCase: GetMediaSetVolumeUseCase
) : ViewModel() {

    var newItem: Boolean = true
    private var _station: RadioStationModel? = null
    var station = MutableLiveData<RadioStationModel?>()

    private val _song = MutableStateFlow<List<String?>>(emptyList())
    var song: StateFlow<List<String?>> = _song.asStateFlow()

    private val _uiIsPlaying = MutableLiveData(false)
    val uiIsPlying: LiveData<Boolean> = _uiIsPlaying


    init {
        viewModelScope.launch {
            while (true) {
                    delay(50)
                if (getExoPlayerUseCase().isPlaying)
                  _song.value = getStationDataUseCase()
                else
                    _song.value = listOf("","")
                station.value = _station
                addListener()
            }
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
            _song.value = listOf(" ", " ")
            getMediaPrepareUseCase()
        }.apply {
            addListener()
        }
    }

    fun addMediaItem(uri: Uri) {
        stop()
        _song.value = listOf(" ", " ")
        viewModelScope.launch {
            getMediaAddItemUseCase(uri)
        }
    }
    fun addStationModel(radioStationModel: RadioStationModel) {
        _station = radioStationModel
    }

    fun play() {
        viewModelScope.launch {
            getMediaPlayUseCase()
            while (uiIsPlying.value != true) {
                delay(50)
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

    fun setVolume(vol: Float) {
        viewModelScope.launch {
            getMediaSetVolumeUseCase(vol)

        }

    }


}
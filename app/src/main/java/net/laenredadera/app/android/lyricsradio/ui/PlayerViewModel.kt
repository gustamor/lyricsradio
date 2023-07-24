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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.PlayingSongInfoState
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumCoverUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaGetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaSetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationAddOnePlayedUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationDataUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    getMediaGetVolumeUseCase: GetMediaGetVolumeUseCase,
    private val getExoPlayerUseCase: GetExoPlayerUseCase,
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
    private val getStationDataUseCase: GetStationDataUseCase,
    private val getMediaSetVolumeUseCase: GetMediaSetVolumeUseCase,
    private val getAlbumCoverUseCase: GetAlbumCoverUseCase,
    private val getRadioStationAddOnePlayedUseCase: GetRadioStationAddOnePlayedUseCase,
) : ViewModel() {

    private var _cover = MutableLiveData("")
    var cover: LiveData<String> = _cover

    private var _volume: Flow<Float> = getMediaGetVolumeUseCase()
    var volume: Flow<Float> = _volume

    private var _station: RadioStationModelUI? = null
    var station = MutableLiveData<RadioStationModelUI?>()

    private var _song = MutableStateFlow(listOf(" ", " "))
    var song: StateFlow<List<String>> = _song.asStateFlow()

    private val _uiIsPlaying = MutableStateFlow(false)
    val uiIsPlaying: StateFlow<Boolean> = _uiIsPlaying.asStateFlow()

    private val _uiIsPaused = MutableStateFlow(false)
    val uiIsPaused: StateFlow<Boolean> = _uiIsPaused.asStateFlow()

    init {
        viewModelScope.launch {
            albumCover()
            addSong()
        }
    }

    private fun addSong() {
        viewModelScope.launch {
            while (true) {
                delay(100)
                if (_uiIsPlaying.value) {
                  getStationDataUseCase().collect {
                        _song.value = when (it) {
                            is PlayingSongInfoState.Error -> {
                                listOf(" ", " ")
                            }

                            is PlayingSongInfoState.Loading -> {
                                listOf(" ", " ")
                            }

                            is PlayingSongInfoState.Success -> {
                                listOf(it.artist, it.title)

                            }

                            is PlayingSongInfoState.Updating -> {
                                listOf(" ", " ")
                            }
                        }
                    }
                } else
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

    fun addMediaItem(uri: Uri) {

        viewModelScope.launch {
            stop()
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

    fun stop() {
        viewModelScope.launch {
            getMediaStopUseCase()
            _uiIsPlaying.value = false
            _uiIsPaused.value = false
        }.apply {
        }
    }

    fun setVolume(vol: Float) {
        viewModelScope.launch {
            getMediaSetVolumeUseCase(vol)
        }
    }

    fun loadImageUrl() {
        viewModelScope.launch {
            delay(200)
            getAlbumCoverUseCase(
                _song.value[0],
                _song.value[1]
            ).collect {
                when (it) {
                    is CoverState.Error -> {
                        _cover.value = ""
                    }

                    is CoverState.Loading -> {
                        _cover.value = " "
                    }

                    is CoverState.Success -> {
                        _cover.value = it.url

                    }
                }
            }
        }
    }

    fun albumCover() {
        viewModelScope.launch {
            try {
                loadImageUrl()
            } catch (e: Exception) {
                throw Exception(e.toString())
            }
        }
    }

    private fun addOnePlayedTime() {
        viewModelScope.launch {
            try {
                getRadioStationAddOnePlayedUseCase(_station!!.id)

            } catch (e: Exception) {
                throw Exception(e.toString())
            }
        }
    }

}
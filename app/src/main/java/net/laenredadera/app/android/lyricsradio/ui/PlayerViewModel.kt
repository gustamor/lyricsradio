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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.CoverState
import net.laenredadera.app.android.lyricsradio.PlayingSongInfoState
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumCoverUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaGetVolumeUseCase
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
    private val getMediaGetVolumeUseCase: GetMediaGetVolumeUseCase,
    private val getAlbumCoverUseCase: GetAlbumCoverUseCase,
    private val getRadioStationAddOnePlayedUseCase: GetRadioStationAddOnePlayedUseCase,
    private val getRadioStationNumberOfTimesPlayedUseCase: GetRadioStationNumberOfTimesPlayedUseCase
) : ViewModel() {

    private var _cover = MutableLiveData("")
    var cover: LiveData<String> = _cover

    private var _volume: Flow<Float> = getMediaGetVolumeUseCase()
    var volume: Flow<Float> = _volume

    private var _station: RadioStationModelUI? = null
    var station = MutableLiveData<RadioStationModelUI?>()

    private var _song = MutableStateFlow(listOf(" ", " "))
    var song: StateFlow<List<String>> = _song.asStateFlow()

    private val _songStateFlow =
        MutableStateFlow<PlayingSongInfoState>(PlayingSongInfoState.Loading)
    var songStateFlow: StateFlow<PlayingSongInfoState> = _songStateFlow.asStateFlow()

    private val _uiIsPlaying = MutableStateFlow(false)
    val uiIsPlaying: StateFlow<Boolean> = _uiIsPlaying.asStateFlow()

    private val _uiIsPaused = MutableStateFlow(false)
    val uiIsPaused: StateFlow<Boolean> = _uiIsPaused.asStateFlow()

    private val _imageUrlFlow = MutableStateFlow<String?>(null)
    val imageUrlFlow: StateFlow<String?> = _imageUrlFlow.asStateFlow()

    private val _imageStateFlow = MutableStateFlow<CoverState>(CoverState.Loading)
    val imageStateFlow: StateFlow<CoverState> = _imageStateFlow.asStateFlow()

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
                   /* getStationDataUseCase().map(PlayingSongInfoState::Success)
                        .catch {PlayingSongInfoState.Error(it)}
                        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PlayingSongInfoState.Loading)*/
                    getStationDataUseCase().collect {
                        _song.value = when (it) {
                            is PlayingSongInfoState.Error -> {
                                Log.i("GusMor _song Error", it.exception.toString())
                                listOf(" ", " ")
                            }
                           is PlayingSongInfoState.Loading -> {
                                Log.i("GusMor _song Loading", it.toString())
                                listOf(" ", " ")
                            }
                            is PlayingSongInfoState.Success -> {
                                Log.i("GusMor _song Success", it.artist + it.title)
                                listOf(it.artist, it.title)

                            }
                            is PlayingSongInfoState.Updating -> {
                                Log.i("GusMor _song Updating", it.toString())
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
                  // addSong()
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
            _uiIsPaused.value = false
        }.apply {
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
            //    _songStateFlow.value = getStationDataUseCase()
            } catch (e: Exception) {
            }
        }
    }

    fun loadImageUrl() {
        viewModelScope.launch {
            delay(200)
            getAlbumCoverUseCase(
                _song.value[0] ?: "",
                _song.value[1] ?: ""
            ).collect {

                when (it) {
                    is CoverState.Error -> {
                        _cover.value = ""
                        Log.i("GusMor _cover Error", it.exception.toString())
                    }

                    is CoverState.Loading -> {
                        _cover.value = " "
                        Log.i("GusMor _cover Loading", it.toString())
                    }

                    is CoverState.Success -> {
                        _cover.value = it.url.toString()
                        Log.i("GusMor _cover Success", it.url.toString())
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
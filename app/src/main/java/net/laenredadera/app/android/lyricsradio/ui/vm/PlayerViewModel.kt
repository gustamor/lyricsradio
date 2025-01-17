package net.laenredadera.app.android.lyricsradio.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.ui.model.PlayingSongInfoState
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumCoverUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumNameUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaGetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaSetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationAddOnePlayedUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationDataUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.CoverState
import net.laenredadera.app.android.lyricsradio.ui.model.PlayerIntent
import net.laenredadera.app.android.lyricsradio.ui.model.PlayerState
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
    private val getAlbumNameUseCase: GetAlbumNameUseCase,
    private val getRadioStationAddOnePlayedUseCase: GetRadioStationAddOnePlayedUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<PlayerState>(PlayerState.Idle)
    val state: StateFlow<PlayerState> = _state.asStateFlow()

    private val _volume = MutableStateFlow(50f)
    val volume: StateFlow<Float> = _volume.asStateFlow()


    fun processIntent(intent: PlayerIntent) {
        viewModelScope.launch {
            when (intent) {
                is PlayerIntent.LoadStation -> handleLoadStation()
                is PlayerIntent.PauseClicked -> handlePauseClicked()
                is PlayerIntent.PlayClicked -> handlePlayClicked()
                is PlayerIntent.VolumeChanged -> handleVolumeChanged(intent.volume)
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun handleLoadStation() {
        _state.value = PlayerState.Loading
        getStationDataUseCase()
            .flatMapLatest { track ->
                when (track) {
                    is PlayingSongInfoState.Error -> flowOf(PlayerState.Error("error"))
                    is PlayingSongInfoState.Loading -> flowOf(PlayerState.Loading)
                    is PlayingSongInfoState.Success -> {
                        getAlbumCoverUseCase(track.artist, track.title)
                            .map { cover ->
                                when (cover) {
                                    is CoverState.Loading -> {
                                        PlayerState.Loading
                                    }

                                    is CoverState.Error -> PlayerState.SongInfo(
                                        artist = track.artist,
                                        title = track.title,
                                        albumCover = null
                                    )

                                    is CoverState.Success -> {
                                        PlayerState.SongInfo(
                                            artist = track.artist,
                                            title = track.title,
                                            albumCover = cover.url
                                        )
                                    }

                                }

                            }
                    }

                    is PlayingSongInfoState.Updating -> flowOf(PlayerState.Loading)
                }
            }.catch { ex ->
                _state.value = PlayerState.Error("Exception: ${ex.message}")
            }


    .collect { playerState ->
        _state.value = playerState as PlayerState.Idle
    }

}
        private fun handlePlayClicked() {
            getMediaPlayUseCase()
            _state.value = PlayerState.Playing
        }
    private fun handlePauseClicked() {
        getMediaStopUseCase()
        _state.value = PlayerState.Paused
    }

    private fun handleVolumeChanged(volume: Float) {
        getMediaSetVolumeUseCase(volume)
        _volume.value = volume
    }


}
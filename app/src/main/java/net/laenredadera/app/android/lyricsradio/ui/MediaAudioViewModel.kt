package net.laenredadera.app.android.lyricsradio.ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.laenredadera.app.android.lyricsradio.AudioVolumeState
import net.laenredadera.app.android.lyricsradio.domain.GetAudioVolumeUseCase
import javax.inject.Inject


@HiltViewModel
class MediaAudioViewModel @Inject constructor(
     getAudioVolumeUseCase: GetAudioVolumeUseCase
) : ViewModel() {

    private val _currentVolume: StateFlow<AudioVolumeState> = getAudioVolumeUseCase().map {
        AudioVolumeState.UpdatedVolume(it)
    }.catch { AudioVolumeState.Error }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),AudioVolumeState.Initialized)
    val currentVolume:StateFlow<AudioVolumeState> = _currentVolume
}
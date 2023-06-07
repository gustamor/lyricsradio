package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaPauseUseCase: GetMediaPauseUseCase,
    private val getMediaPrepareUseCase: GetMediaPrepareUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
) : ViewModel() {

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
        }
    }

    fun pause() {
        viewModelScope.launch {
            getMediaPauseUseCase()
        }
    }

    fun stop() {
        viewModelScope.launch {
            getMediaStopUseCase()
        }
    }
}
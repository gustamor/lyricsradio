package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.BuildConfig
import net.laenredadera.app.android.lyricsradio.ui.domain.GetExoPlayerUseCase
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(getExoPlayerUseCase: GetExoPlayerUseCase) : ViewModel() {

    private var _isPlaying = false
    var isPlaying: Boolean = _isPlaying
    private val player = getExoPlayerUseCase()
    fun prepare() {
        if (BuildConfig.DEBUG) player.addAnalyticsListener(EventLogger())
        player.prepare()
    }

    fun addMediaItem(uri: Uri) {
        viewModelScope.launch {
            if (player.mediaItemCount > 0) player.clearMediaItems()
            player.addMediaItem(MediaItem.fromUri(uri))
        }
    }

    fun play() {
        viewModelScope.launch {
           if (_isPlaying) {
               stop()
               prepare()

           } else {
               player.play()
               _isPlaying = true
           }

        }
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        player.stop()
        _isPlaying = false

    }
}
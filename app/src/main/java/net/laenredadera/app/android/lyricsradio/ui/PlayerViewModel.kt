package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
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
class PlayerViewModel @Inject constructor(private val getExoPlayerUseCase: GetExoPlayerUseCase): ViewModel() {

   private val player = getExoPlayerUseCase()

    fun prepare() {
        if (BuildConfig.DEBUG) player.addAnalyticsListener(EventLogger())
        player.prepare()
    }

    fun addMediaItem(uri: Uri) {
        viewModelScope.launch {

            player.addMediaItem(MediaItem.fromUri(uri))
        }
    }

    fun play() {
        viewModelScope.launch {
            player.playWhenReady = true
        }

    }

    fun pause() {
        viewModelScope.launch {
            player.pause()
        }

    }
}
package net.laenredadera.app.android.lyricsradio.data.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import kotlinx.coroutines.coroutineScope
import net.laenredadera.app.android.lyricsradio.BuildConfig
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import javax.inject.Inject

class RadioReceiverService @Inject constructor(private val player: ExoPlayer) : Service() {
    private var _isPlaying by mutableStateOf(false)
    var isPlaying: Boolean = _isPlaying

    fun initPlayer() {
        player.prepare()
    }

    fun prepare(){
        if (BuildConfig.DEBUG) player.addAnalyticsListener(EventLogger())
        player.prepare()
    }

    fun play(){
        if (_isPlaying) {
            stop()
            prepare()
        } else {
            player.play()
            _isPlaying = true
        }
    }

    fun pause(){
        player.pause()
        _isPlaying = false
    }
    fun stop(){
        player.stop()
        _isPlaying = false
    }
    fun addMedia(uri: Uri){
        if (player.mediaItemCount > 0) player.clearMediaItems()
        player.addMediaItem(MediaItem.fromUri(uri))
    }
    fun relase(){
        player.stop()
        _isPlaying = false
        player.release()
    }
   override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
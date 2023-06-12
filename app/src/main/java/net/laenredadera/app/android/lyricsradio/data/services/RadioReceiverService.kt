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

/**
 * Radio receiver service
 *
 * @property player
 * @constructor Create empty Radio receiver service
 */
class RadioReceiverService @Inject constructor(private val player: ExoPlayer) : Service() {

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.action == Intent.ACTION_MEDIA_BUTTON) {
        //    mediaSessionComponent.handleMediaButtonIntent(intent)
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    /**
     * use this function to prepare de player
     *
     */
    fun initPlayer() {
        prepare()
    }

    /**
     * Prepare the player
     *
     */
    fun prepare(){
        if (BuildConfig.DEBUG) player.addAnalyticsListener(EventLogger())
        player.prepare()
    }

    /**
     * Plays the player
     *
     */
    fun play(){
        prepare()
      //  player.play()
       player.playWhenReady = true
    }

    /**
     * Pause the player
     *
     */
    fun pause(){
        player.pause()
    }

    /**
     * Stop the player
     *
     */
    fun stop(){
        player.stop()
    }

    /**
     * Add media
     *
     * @param uri
     */
    fun addMedia(uri: Uri){
        if (player.mediaItemCount > 0) player.clearMediaItems()
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    /**
     * Release
     *
     */
    fun release(){
        player.stop()
        player.release()
    }

}
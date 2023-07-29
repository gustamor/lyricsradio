package net.laenredadera.app.android.lyricsradio.data.services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Metadata
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.extractor.metadata.icy.IcyHeaders
import androidx.media3.extractor.metadata.icy.IcyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.BuildConfig
import net.laenredadera.app.android.lyricsradio.R
import javax.inject.Inject

/**
 * Radio receiver service
 *
 * @property player
 * @constructor Create empty Radio receiver service
 */
class RadioReceiverService @Inject constructor(private val player: ExoPlayer) : Service() {

    private val _isPlaying = MutableStateFlow(false)
    var isPlaying = _isPlaying.asStateFlow()

    private val _artistName = MutableStateFlow(" ")
    var artistName = _artistName.asStateFlow()

    private val _songName = MutableStateFlow(" ")
    var songName = _songName.asStateFlow()
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        if (intent.action == Intent.ACTION_MEDIA_BUTTON) {
            //    mediaSessionComponent.handleMediaButtonIntent(intent)
        }
        return START_NOT_STICKY

    }

    private fun stopService() {
        stopForeground(true)
        stopSelf()
    }

    /**
     * use this function to prepare de player
     *
     */
    fun initPlayer() {
        prepare()
        getVolume()
    }

    /**
     * Prepare the player
     *
     */
    fun prepare() {
        if (BuildConfig.DEBUG) player.addAnalyticsListener(EventLogger())
        player.prepare()
    }

    /**
     * Plays the player
     *
     */
    fun play() {
        prepare()
        player.playWhenReady = true
        _isPlaying.value = true
    }

    /**
     * Pause the player
     *
     */
    fun pause() {
        player.pause()
        _isPlaying.value = true
        _artistName.value = " "
        _songName.value = " "
    }

    /**
     * Stop the player
     *
     */
    fun stop() {
        player.stop()
        _artistName.value = " "
        _songName.value = " "
        _isPlaying.value = false
    }

    fun getVolume():Float {
       return player.volume
    }

    /**
     * Add media
     *
     * @param uri
     */
    fun addMedia(uri: Uri) {
        if (player.mediaItemCount > 0) player.clearMediaItems()
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    /**
     * Release
     *
     */
    fun release() {
        player.stop()
        player.release()
        _artistName.value = " "
        _songName.value = " "
    }

    suspend fun icyMetadata() {
        coroutineScope {
            withContext(Dispatchers.Main) {
                    player.addAnalyticsListener(@UnstableApi object : AnalyticsListener {
                        override fun onMetadata(
                            eventTime: AnalyticsListener.EventTime,
                            metadata: Metadata
                        ) {
                            super.onMetadata(eventTime, metadata)

                            for (i in 0 until metadata.length()) {
                                val info = metadata[i]

                                if (info is IcyInfo) {

                                    val _artistTitle: String = info.title.orEmpty()
                                    val partes = _artistTitle.split(" - ")
                                    if (partes.size >= 2) {
                                        _artistName.value = partes[0].toString()
                                        _songName.value = partes[1].toString()
                                    } else {
                                        _artistName.value = " "
                                        _songName.value = " "
                                    }
                                } else {
                                    _artistName.value = " "
                                    _songName.value = " "
                                }
                            }
                        }
                    })
                }
            }
    }

    fun setVolume(vol: Float) {
        player.volume = vol
    }

}


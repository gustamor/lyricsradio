package net.laenredadera.app.android.lyricsradio.data.repositories

import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val exoPlayer: ExoPlayer) {
    fun get() = exoPlayer
}
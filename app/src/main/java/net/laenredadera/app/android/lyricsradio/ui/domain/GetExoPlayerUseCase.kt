package net.laenredadera.app.android.lyricsradio.ui.domain

import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

class GetExoPlayerUseCase @Inject constructor(private val player: ExoPlayer) {

     operator fun invoke() = player

}
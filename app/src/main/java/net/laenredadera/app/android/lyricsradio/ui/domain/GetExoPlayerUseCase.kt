package net.laenredadera.app.android.lyricsradio.ui.domain

import androidx.media3.exoplayer.ExoPlayer
import net.laenredadera.app.android.lyricsradio.data.PlayerRepository
import net.laenredadera.app.android.lyricsradio.data.RadioStationsRepository
import javax.inject.Inject

class GetExoPlayerUseCase @Inject constructor(private val player: PlayerRepository) {

     operator fun invoke() = player.get()

}
package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.PlayerRepository
import javax.inject.Inject

class GetExoPlayerUseCase @Inject constructor(private val player: PlayerRepository) {

     operator fun invoke() = player.get()

}
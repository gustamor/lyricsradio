package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.LyricsRepository
import net.laenredadera.app.android.lyricsradio.domain.model.LyricsModel
import net.laenredadera.app.android.lyricsradio.ui.model.LyricsModelUI
import javax.inject.Inject

class GetLyricsUseCase @Inject constructor(private val repository: LyricsRepository) {

    suspend operator fun invoke(artist:String, title:String): LyricsModel {
        return repository.getLyrics(artist,title)
    }
}
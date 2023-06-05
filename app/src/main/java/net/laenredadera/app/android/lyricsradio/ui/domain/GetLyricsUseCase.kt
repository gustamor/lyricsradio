package net.laenredadera.app.android.lyricsradio.ui.domain

import net.laenredadera.app.android.lyricsradio.data.LyricsRepository
import net.laenredadera.app.android.lyricsradio.ui.model.LyricsModel
import javax.inject.Inject

class GetLyricsUseCase @Inject constructor(private val repository: LyricsRepository) {

    suspend operator fun invoke(artist:String, title:String): LyricsModel {
        return repository.getLyrics(artist,title)
    }
}
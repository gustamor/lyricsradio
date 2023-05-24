package net.laenredadera.app.android.lyricsradio.data
import net.laenredadera.app.android.lyricsradio.data.services.LyricsService
import net.laenredadera.app.android.lyricsradio.ui.model.LyricsModel
import net.laenredadera.app.android.lyricsradio.ui.model.toData
import javax.inject.Inject

class LyricsRepository @Inject constructor(private val service: LyricsService){

    suspend fun getLyrics(artist:String,title:String):LyricsModel =
        service.getLyrics(artist,title).toData()

}
package net.laenredadera.app.android.lyricsradio.data.services

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.services.network.LyricsApiClient
import net.laenredadera.app.android.lyricsradio.data.services.network.model.LyricsResponse
import javax.inject.Inject

class LyricsService @Inject constructor(private val api: LyricsApiClient) {

    suspend fun getLyrics(artist:String, title:String): LyricsResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getLyrics(artist,title)
            Log.i("GusMor", response.toString())
            (if (response.code() == 429) {
                "El temido código 429"
            } else if (response.code() == 200) {
                response.body()!!;
            } else {
                throw Exception("Lyrics API call failed")

            }) as LyricsResponse
        }
    }
}
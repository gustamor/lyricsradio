package net.laenredadera.app.android.lyricsradio.data.services.network

import net.laenredadera.app.android.lyricsradio.data.services.network.model.LyricsResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LyricsApiClient {

    @FormUrlEncoded
    @POST("lyrics")
    suspend fun getLyrics(@Field("artist") artist:String, @Field("title") title:String) : Response<LyricsResponse>

}
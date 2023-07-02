package net.laenredadera.app.android.lyricsradio.data.services.network

import net.laenredadera.app.android.lyricsradio.data.services.network.model.AlbumInfoResponse
import net.laenredadera.app.android.lyricsradio.data.services.network.model.TrackInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFMApiClient {
    @GET("2.0/?method=track.getInfo&format=json")
    suspend fun getTrackInfo(
        @Query("api_key") api_lastfm_key: String,
        @Query("artist") artistName: String,
        @Query("track") trackName: String,
    ): Response<TrackInfoResponse>

    @GET("2.0/?method=album.getinfo&format=json")
    suspend fun getAlbumInfo(
        @Query("api_key") api_lastfm_key: String,
        @Query("mbid") mbid: String
    ): Response<AlbumInfoResponse>
}
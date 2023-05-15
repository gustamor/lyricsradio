package net.laenredadera.app.android.lyricsradio.data.network

import net.laenredadera.app.android.lyricsradio.data.model.RadioStationsDataResponse
import retrofit2.Response
import retrofit2.http.GET
interface RadioStationsApiClient {
    @GET("3ca4a39444bc3d2927c6")
    suspend fun getRadioStations() : Response<List<RadioStationsDataResponse>>
}

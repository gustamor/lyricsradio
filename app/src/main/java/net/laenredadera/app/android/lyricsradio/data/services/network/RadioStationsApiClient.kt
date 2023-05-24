package net.laenredadera.app.android.lyricsradio.data.services.network

import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationsDataResponse
import retrofit2.Response
import retrofit2.http.GET
interface RadioStationsApiClient {
    @GET("d8318bea95b9929dcf00")
    suspend fun getRadioStations() : Response<List<RadioStationsDataResponse>>
}

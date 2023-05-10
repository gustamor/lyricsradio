package net.laenredadera.app.android.lyricsradio.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.core.RetrofitBuilder
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem

class RetrofitService {

    private val retrofit = RetrofitBuilder.getRetrofit()

    suspend fun getAllRadioStations(): List<RadioStationItem> {
      return withContext(Dispatchers.IO) {
          val response = retrofit.create(RadioStationsApiClient::class.java).getRadioStations()
          if (response.isSuccessful) {
              val responseData = response.body()
              responseData?.get(0)?.stations ?: emptyList()
          } else {
              throw Exception("API call failed")
          }
      }
    }

}

package net.laenredadera.app.android.lyricsradio.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitService @Inject constructor(private val api: RadioStationsApiClient) {


    suspend fun getAllRadioStations(): List<RadioStationItem> {
      return withContext(Dispatchers.IO) {
          val response = api.getRadioStations()
          if (response.isSuccessful) {
              val responseData = response.body()
              responseData?.get(0)?.stations ?: emptyList()
          } else {
              throw Exception("API call failed")
          }
      }
    }
}

package net.laenredadera.app.android.lyricsradio.data.repositories

import net.laenredadera.app.android.lyricsradio.data.services.RetrofitService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.domain.model.toData

import javax.inject.Inject

class RadioStationsRepository @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllRadioStations(): List<RadioStationModel> {
        val response: List<RadioStationItem> = retrofitService.getAllRadioStations()
        return response.map { it.toData() }
    }
}
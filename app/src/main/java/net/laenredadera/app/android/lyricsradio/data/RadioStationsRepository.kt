package net.laenredadera.app.android.lyricsradio.data

import dagger.Module
import net.laenredadera.app.android.lyricsradio.data.network.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.network.RadioStationsApiClient
import net.laenredadera.app.android.lyricsradio.data.network.RetrofitService
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.ui.model.toData
import javax.inject.Inject

class RadioStationsRepository @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllRadioStations(): List<RadioStationModel>? {
        val response: List<RadioStationItem> = retrofitService.getAllRadioStations() ?: emptyList()
        return response.map { it.toData() }
    }
}
package net.laenredadera.app.android.lyricsradio.data

import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.services.RadioStationsListService
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.ui.model.toData
import javax.inject.Inject

class RadioStationsRepository @Inject constructor(private val service: RadioStationsListService) {

    suspend fun getAllRadioStations(): List<RadioStationModel>? {
        val response: List<RadioStationItem> = service.getAllRadioStations() ?: emptyList()
        return response.map { it.toData() }
    }
}
package net.laenredadera.app.android.lyricsradio.data

import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.network.RetrofitService

class RadioStationsRepository {
    private val retrofitService = RetrofitService()

    suspend fun getAllRadioStations() : List<RadioStationItem> = retrofitService.getAllRadioStations()
}
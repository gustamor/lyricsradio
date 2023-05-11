package net.laenredadera.app.android.lyricsradio.data

import dagger.Module
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.network.RadioStationsApiClient
import net.laenredadera.app.android.lyricsradio.data.network.RetrofitService
import javax.inject.Inject

class RadioStationsRepository @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllRadioStations(): List<RadioStationItem>? {
        return retrofitService.getAllRadioStations()
    }
}
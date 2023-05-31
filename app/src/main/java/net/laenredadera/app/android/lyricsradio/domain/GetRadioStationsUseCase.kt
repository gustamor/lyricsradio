package net.laenredadera.app.android.lyricsradio.domain

import androidx.lifecycle.MutableLiveData
import net.laenredadera.app.android.lyricsradio.data.RadioStationsRepository
import net.laenredadera.app.android.lyricsradio.data.network.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import javax.inject.Inject

class GetRadioStationsUseCase @Inject constructor(private val repository: RadioStationsRepository)  {
    suspend operator  fun invoke(): List<RadioStationModel>? {
        return repository.getAllRadioStations()
    }

}
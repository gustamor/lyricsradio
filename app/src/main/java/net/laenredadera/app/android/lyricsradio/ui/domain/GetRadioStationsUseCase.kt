package net.laenredadera.app.android.lyricsradio.ui.domain

import androidx.lifecycle.MutableLiveData
import net.laenredadera.app.android.lyricsradio.data.RadioStationsRepository
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import javax.inject.Inject

class GetRadioStationsUseCase @Inject constructor(private val repository: RadioStationsRepository)  {
    suspend operator  fun invoke(): List<RadioStationItem>? {
        return repository.getAllRadioStations()
    }

}
package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.RadioStationsRepository
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import javax.inject.Inject

class GetRadioStationsUseCase @Inject constructor(private val repository: RadioStationsRepository)  {
    suspend operator fun invoke(): List<RadioStationModel>? {
        return repository.getAllRadioStations()
    }

}
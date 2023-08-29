package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.RadioStationsRepository
import javax.inject.Inject

class GetRadioStationNumberOfTimesPlayedUseCase @Inject constructor(private val radioStationsRepository: RadioStationsRepository) {
   suspend operator fun invoke(stationId : Int) = radioStationsRepository.getNumberOfTimesPlayed(stationId)

}


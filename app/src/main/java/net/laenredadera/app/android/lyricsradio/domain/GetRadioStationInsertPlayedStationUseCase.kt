package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.RadioStationsRepository
import net.laenredadera.app.android.lyricsradio.domain.model.MostPlayedStations
import javax.inject.Inject

class GetRadioStationInsertPlayedStationUseCase @Inject constructor(private val radioStationsRepository: RadioStationsRepository) {

   suspend operator fun invoke(station : MostPlayedStations) = radioStationsRepository.insertMostPlayedStationEntity(station)
}
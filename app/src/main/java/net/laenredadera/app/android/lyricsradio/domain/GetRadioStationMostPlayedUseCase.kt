package net.laenredadera.app.android.lyricsradio.domain

import kotlinx.coroutines.flow.Flow
import net.laenredadera.app.android.lyricsradio.data.repositories.RadioStationsRepository
import net.laenredadera.app.android.lyricsradio.domain.model.TopStationsModel
import javax.inject.Inject

class GetRadioStationMostPlayedUseCase @Inject constructor(private val radioStationsRepository: RadioStationsRepository) {

    operator fun invoke(): Flow<List<TopStationsModel?>> = radioStationsRepository.getOrderedTopStations()

}


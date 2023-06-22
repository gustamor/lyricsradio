package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.OnlineStationRepository
import javax.inject.Inject

class GetStationNameUseCase   @Inject constructor(private val stationsRepository: OnlineStationRepository) {
    suspend operator fun invoke() {

    }

}
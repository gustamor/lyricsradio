package net.laenredadera.app.android.lyricsradio.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import net.laenredadera.app.android.lyricsradio.data.OnlineStationRepository
import javax.inject.Inject

class GetStationDataUseCase @Inject constructor(private val stationsRepository: OnlineStationRepository) {
    suspend operator fun invoke(): List<String?> {
        return listOf(stationsRepository.getArtist(), stationsRepository.getTitle())
    }
}
package net.laenredadera.app.android.lyricsradio.domain


import kotlinx.coroutines.flow.Flow
import net.laenredadera.app.android.lyricsradio.PlayingSongInfoState
import net.laenredadera.app.android.lyricsradio.data.repositories.OnlineStationRepository
import javax.inject.Inject

class GetStationDataUseCase @Inject constructor(private val stationsRepository: OnlineStationRepository) {
    suspend operator fun invoke(): Flow<PlayingSongInfoState> = stationsRepository.songInfo()

}
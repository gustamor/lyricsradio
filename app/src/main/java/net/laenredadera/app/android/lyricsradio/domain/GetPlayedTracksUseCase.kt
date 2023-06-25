package net.laenredadera.app.android.lyricsradio.domain

import kotlinx.coroutines.flow.Flow
import net.laenredadera.app.android.lyricsradio.data.repositories.TracksRepository
import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import javax.inject.Inject

class GetPlayedTracksUseCase @Inject constructor(private val playedTracksRepository: TracksRepository) {
    operator fun invoke(): Flow<List<PlayedTrackDataModel>> = playedTracksRepository.getTracksInfo()
}
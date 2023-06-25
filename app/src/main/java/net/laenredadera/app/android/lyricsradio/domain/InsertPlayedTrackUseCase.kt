package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.TracksRepository
import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import javax.inject.Inject

class InsertPlayedTrackUseCase @Inject constructor(private val playedTracksRepository: TracksRepository) {
    suspend operator fun invoke(track: PlayedTrackDataModel) =
        playedTracksRepository.insertPlayedTrack(track)
}
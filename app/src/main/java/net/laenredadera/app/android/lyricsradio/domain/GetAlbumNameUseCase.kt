package net.laenredadera.app.android.lyricsradio.domain

import kotlinx.coroutines.flow.Flow
import net.laenredadera.app.android.lyricsradio.data.repositories.MediaInfoRepository
import javax.inject.Inject

class GetAlbumNameUseCase  @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {
    suspend operator fun invoke(artist: String, trackName: String): Flow<String> = mediaInfoRepository.getAlbumName(artist,trackName)
}
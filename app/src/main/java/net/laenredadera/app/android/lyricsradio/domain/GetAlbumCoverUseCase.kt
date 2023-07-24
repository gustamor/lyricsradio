package net.laenredadera.app.android.lyricsradio.domain

import kotlinx.coroutines.flow.Flow
import net.laenredadera.app.android.lyricsradio.data.repositories.MediaInfoRepository
import net.laenredadera.app.android.lyricsradio.ui.CoverState
import javax.inject.Inject

class GetAlbumCoverUseCase @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {
    suspend operator  fun invoke(artistName : String, trackName: String): Flow<CoverState> = mediaInfoRepository.getAlbumCover(artistName,trackName)

}
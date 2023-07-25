package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.MediaInfoRepository
import javax.inject.Inject

class GetAlbumNameFromMbIDUseCase  @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {
    suspend operator fun invoke(MbID: String): String = mediaInfoRepository.getAlbumNameByMbID(MbID)

}
package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.MediaInfoRepository
import javax.inject.Inject

class GetAlbumCoverUseCase @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {

    suspend operator  fun invoke(artistName : String, trackName: String): String = mediaInfoRepository.getAlbumCover(artistName,trackName)

}
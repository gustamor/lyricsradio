package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.MediaInfoRepository
import javax.inject.Inject

class GetArtistMbIdUseCase  @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {
    suspend operator  fun invoke(artistName : String, trackName: String): String = mediaInfoRepository.getArtistMbId(artistName,trackName)

}
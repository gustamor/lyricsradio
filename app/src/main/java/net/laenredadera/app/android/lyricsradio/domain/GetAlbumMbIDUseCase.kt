package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.MediaInfoRepository
import javax.inject.Inject

class GetAlbumMbIDUseCase  @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {

    suspend operator fun invoke(artist: String, trackName: String): String = mediaInfoRepository.getAlbumMbId(artist,trackName)

}
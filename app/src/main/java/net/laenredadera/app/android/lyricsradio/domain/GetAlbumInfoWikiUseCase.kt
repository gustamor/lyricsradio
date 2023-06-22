package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.MediaInfoRepository
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import javax.inject.Inject

class GetAlbumInfoWikiUseCase @Inject constructor(private val mediaInfoRepository: MediaInfoRepository) {

    suspend operator fun invoke(mbid: String): Wiki = mediaInfoRepository.getAlbumInfoWiki(mbid)

}
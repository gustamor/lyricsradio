package net.laenredadera.app.android.lyricsradio.data

import net.laenredadera.app.android.lyricsradio.data.services.LastFMService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import javax.inject.Inject

class MediaInfoRepository @Inject constructor(private val lastFM: LastFMService) {

    suspend fun getAlbumMbId(artistName: String, trackName: String): String {
       return lastFM.getAlbumMbId(artistName, trackName)
    }

    suspend fun getAlbumCover(artistName: String, trackName: String): String {
            return lastFM.getAlbumCover(artistName,trackName)
    }

    suspend fun getAlbumInfoWiki(mbid: String): Wiki {

        return lastFM.getAlbumInfoWiki(mbid)
    }

}

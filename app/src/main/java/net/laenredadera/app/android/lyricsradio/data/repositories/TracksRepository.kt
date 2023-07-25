package net.laenredadera.app.android.lyricsradio.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.db.TracksDao
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumCoverUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumMbIDUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumNameFromMbIDUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetArtistMbIdUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetItemArtistNameUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetItemSongTitleUseCase
import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import net.laenredadera.app.android.lyricsradio.domain.model.toData
import javax.inject.Inject

class TracksRepository @Inject constructor(
    private val dao: TracksDao,

) {
    suspend fun insertPlayedTrack(track: PlayedTrackDataModel) {
        withContext(Dispatchers.IO) {
            dao.insertPlayedTrack(track.toData())
            Log.i("GusMorTrackToData", track.toData().toString())
        }
    }

    fun getTracksInfo(): Flow<List<PlayedTrackDataModel>> =
        dao.getPlayedTracks().map { track -> track.map { it.toData() } }

    suspend fun addTrack(playedTrack: PlayedTrackDataModel) {
        dao.insertPlayedTrack(playedTrack.toData())
    }


}

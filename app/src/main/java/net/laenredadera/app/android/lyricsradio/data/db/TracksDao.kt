package net.laenredadera.app.android.lyricsradio.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {

    @Query("SELECT * FROM TrackEntity")
    fun getTracks(): Flow<List<TrackEntity>>

    @Query("SELECT TrackEntity.MbID, TrackEntity.songName, AlbumEntity.name as albumName, " +
            "ArtistEntity.name as artistName, PlayedTrackEntity.playedAt," +
            "TrackEntity.radioStationName " +
            "FROM PlayedTrackEntity " +
            "INNER JOIN TrackEntity ON PlayedTrackEntity.trackMbID = TrackEntity.MbID" +
            " INNER JOIN AlbumEntity ON TrackEntity.albumMbID = AlbumEntity.MbID " +
            "INNER JOIN ArtistEntity ON TrackEntity.artistMbID = ArtistEntity.MbID")
    fun getPlayedTracks(): Flow<List<PlayedTrackDataEntity>>

     @Query("SELECT TrackEntity.MbID, TrackEntity.songName, AlbumEntity.name as albumName," +
             " ArtistEntity.name as artistName, PlayedTrackEntity.playedAt," +
             "TrackEntity.radioStationName " +
             "FROM PlayedTrackEntity " +
             "INNER JOIN TrackEntity ON PlayedTrackEntity.trackMbID = TrackEntity.MbID "+
             "INNER JOIN ArtistEntity ON TrackEntity.artistMbID = ArtistEntity.MbID " +
             "INNER JOIN AlbumEntity ON TrackEntity.albumMbID = AlbumEntity.MbID " +
             "WHERE  PlayedTrackEntity.trackMbID = :playedTrackId")
     fun getTrackFromAPlayedTrackMbID(playedTrackId: String): Flow<PlayedTrackDataEntity>

    @Query("SELECT AlbumEntity.cover FROM AlbumEntity WHERE AlbumEntity.MbId = :albumMbID ")
    fun getAlbumCover(albumMbID: String): Flow<String>


}

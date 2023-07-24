package net.laenredadera.app.android.lyricsradio.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TracksDao {
    @Query("SELECT * FROM TrackEntity")
    fun getTracks(): Flow<List<TrackEntity>>
    @Query("SELECT * FROM TopStationEntity WHERE TopStationEntity.enabled = 1 ORDER BY numTimesPlayed DESC")
    fun getOrderedTopStations(): Flow<List<TopStationEntity?>>
    @Query("SELECT * FROM TopStationEntity WHERE id = :id")
    fun getTopStationEntity(id: Int): TopStationEntity
    @Query("UPDATE TopStationEntity SET numTimesPlayed = numTimesPlayed + 1 WHERE id = :id ")
    fun updateTopStationEntity(id: Int)
    @Query("SELECT numTimesPlayed FROM TopStationEntity WHERE id = :id")
    fun getNumberStationTimesPlayed(id: Int): Int

    @Query("UPDATE TopStationEntity SET lastTimePlayed = :ms WHERE id = :id ")
    fun setLastTimePlayed(id: Int, ms: Long)

    @Query("SELECT lastTimePlayed FROM TopStationEntity WHERE id = :id")
    fun getLastTimePlayed(id: Int): Long

    @Query(
        "SELECT TrackEntity.MbID, TrackEntity.name, AlbumEntity.name as albumName, " +
                "ArtistEntity.name as artistName, PlayedTrackEntity.playedAt," +
                "TrackEntity.radioStationName, AlbumEntity.cover as cover " +
                "FROM PlayedTrackEntity " +
                "INNER JOIN TrackEntity ON PlayedTrackEntity.trackMbID = TrackEntity.MbID " +
                "INNER JOIN AlbumEntity ON TrackEntity.albumMbID = AlbumEntity.MbID " +
                "INNER JOIN ArtistEntity ON TrackEntity.artistMbID = ArtistEntity.MbID"
    )
    fun getPlayedTracks(): Flow<List<PlayedTrackDataEntity>>

    @Query(
        "SELECT TrackEntity.MbID, TrackEntity.name, AlbumEntity.name as albumName, " +
                "ArtistEntity.name as artistName, PlayedTrackEntity.playedAt," +
                "TrackEntity.radioStationName, AlbumEntity.cover as cover " +
                "FROM PlayedTrackEntity " +
                "INNER JOIN TrackEntity ON PlayedTrackEntity.trackMbID = TrackEntity.MbID " +
                "INNER JOIN ArtistEntity ON TrackEntity.artistMbID = ArtistEntity.MbID " +
                "INNER JOIN AlbumEntity ON TrackEntity.albumMbID = AlbumEntity.MbID " +
                "WHERE  PlayedTrackEntity.trackMbID = :playedTrackId"
    )
    fun getTrackFromAPlayedTrackMbID(playedTrackId: String): Flow<PlayedTrackDataEntity>

    @Query("SELECT AlbumEntity.cover FROM AlbumEntity WHERE AlbumEntity.MbId = :albumMbID ")
    fun getAlbumCover(albumMbID: String): Flow<String>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMostPlayedStationEntity(stationEntity: TopStationEntity)
    @Insert
    suspend fun insertPlayedTrack(track: PlayedTrackDataEntity)
    @Insert
    fun insertTrack(track: TrackEntity)
    @Insert
    fun insertAlbum(album: AlbumEntity)

    @Insert
    fun insertArtist(artist: ArtistEntity)

    @Insert
    fun insertWiki(wiki: WikiEntity)

    @Delete
    fun deleteMostPlayedTracks(stationEntity: TopStationEntity)

    @Delete
    fun deletePlayedTrack(track: PlayedTrackDataEntity)

    @Delete
    fun deleteTrack(track: TrackEntity)

    @Delete
    fun deleteAlbum(album: AlbumEntity)

    @Delete
    fun deleteArtist(artist: ArtistEntity)

    @Delete
    fun deleteWiki(wiki: WikiEntity)


}

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
    @Query("SELECT * FROM MostPlayedStationEntity WHERE MostPlayedStationEntity.enabled = 1 ORDER BY numTimesPlayed DESC")
    fun getOrderedMostPlayedStation(): Flow<List<MostPlayedStationEntity?>>
    @Query("SELECT * FROM MostPlayedStationEntity WHERE id = :id")
    fun getMostPlayedStationEntity(id: Int): MostPlayedStationEntity
    @Query("UPDATE MostPlayedStationEntity SET numTimesPlayed = numTimesPlayed +1 WHERE name = :id ")
    suspend fun updateMostPlayedStationEntity(id: Int)
    @Query("SELECT numTimesPlayed FROM MostPlayedStationEntity WHERE id = :id")
    fun getNumberOfTimesPlayed(id: Int): Int

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
    suspend fun insertMostPlayedStationEntity(stationEntity: MostPlayedStationEntity)
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
    fun deleteMostPlayedTracks(stationEntity: MostPlayedStationEntity)

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

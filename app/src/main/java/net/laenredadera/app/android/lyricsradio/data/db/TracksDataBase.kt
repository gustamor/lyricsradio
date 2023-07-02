package net.laenredadera.app.android.lyricsradio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [PlayedTrackDataEntity::class, WikiEntity::class, PlayedTrackEntity::class, TrackEntity::class, ArtistEntity::class, AlbumEntity::class,MostPlayedStationEntity::class],
    version = 1
)
abstract class TracksDataBase : RoomDatabase() {
    abstract fun tracksDao(): TracksDao
}

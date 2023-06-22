
package net.laenredadera.app.android.lyricsradio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayedTrackEntity::class, TrackEntity::class, ArtistEntity::class, AlbumEntity::class ] , version = 1)
abstract class TracksDataBase:RoomDatabase() {
    abstract fun tracksDao():TracksDao
}

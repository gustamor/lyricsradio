
package net.laenredadera.app.android.lyricsradio.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TrackEntity(
    @PrimaryKey
    var MbID: String,
    var songName: String,
    var artistMbID: String,
    var albumMbID: String,
    var radioStationName: String,
)

@Entity
data class AlbumEntity(
    @PrimaryKey
    var MbID: String,
    var name: String,
    var artist: String,
    var cover: String,
    var wikiId: String
)

@Entity
data class ArtistEntity(
    @PrimaryKey
    var MbID: String,
    var name: String
)

@Entity
data class WikiEntity(
    @PrimaryKey (autoGenerate = true)
    var id: Long,
    var MbID: String,
    var summary: String,
    var content: String
)

@Entity
data class PlayedTrackEntity(
    @PrimaryKey (autoGenerate = true)
    var id: Long,
    var trackMbID: String,
    var playedAt: String,
)

data class PlayedTrackDataEntity(
    @PrimaryKey
    var MbID: String,
    var songName: String,
    var albumName: String,
    var artistName: String,
    var radioStationName: String,
    var playedAt: String
)

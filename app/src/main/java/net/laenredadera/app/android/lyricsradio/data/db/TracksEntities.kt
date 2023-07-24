
package net.laenredadera.app.android.lyricsradio.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackEntity(
    @PrimaryKey
    var MbID: String,
    var name: String,
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
@Entity
data class PlayedTrackDataEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var MbID: String,
    var name: String,
    var artistName: String,
    var albumName: String,
    var cover: String,
    var radioStationName: String,
    val playedAt: String
)

@Entity
data class TopStationEntity(
    @PrimaryKey
    var id: Int,
    var enabled: Boolean = true,
    var name: String,
    var cover: String,
    var description: String,
    var lastTimePlayed: Long?,
    var numTimesPlayed: Int?,
)


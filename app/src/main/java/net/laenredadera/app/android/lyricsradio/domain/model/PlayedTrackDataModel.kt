package net.laenredadera.app.android.lyricsradio.domain.model

import net.laenredadera.app.android.lyricsradio.data.db.PlayedTrackDataEntity
import java.util.Date


data class PlayedTrackDataModel(
     var MbID: String,
     var name: String,
     var artistName: String,
     var albumName: String,
     var cover: String,
     var radioStationName: String,
     val playedAt: String
)

fun PlayedTrackDataModel.toData() = PlayedTrackDataEntity(MbID, name, artistName, albumName, radioStationName, cover, playedAt)
fun PlayedTrackDataEntity.toData() = PlayedTrackDataModel(MbID, name, artistName, albumName, radioStationName, cover, playedAt)
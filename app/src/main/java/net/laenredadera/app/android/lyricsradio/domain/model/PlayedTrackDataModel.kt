package net.laenredadera.app.android.lyricsradio.domain.model

import net.laenredadera.app.android.lyricsradio.data.db.PlayedTrackDataEntity
import net.laenredadera.app.android.lyricsradio.ui.model.PlayedTrackModelUI

data class PlayedTrackDataModel(
     var id: Int,
     var MbID: String,
     var name: String,
     var artistName: String,
     var albumName: String,
     var cover: String,
     var radioStationName: String,
     val playedAt: String
)

fun PlayedTrackDataModel.toDataUI() = PlayedTrackModelUI(id, MbID, name, artistName, albumName, cover, radioStationName,  playedAt)

fun PlayedTrackDataModel.toData() = PlayedTrackDataEntity(id, MbID, name, artistName, albumName, radioStationName, cover, playedAt)
fun PlayedTrackDataEntity.toData() = PlayedTrackDataModel(id, MbID, name, artistName, albumName, radioStationName, cover, playedAt)
package net.laenredadera.app.android.lyricsradio.domain.model

import net.laenredadera.app.android.lyricsradio.data.db.PlayedTrackDataEntity
import net.laenredadera.app.android.lyricsradio.ui.model.PlayedTrackModelUI

data class PlayedTrackDataModel(
     var MbID: String,
     var name: String,
     var artistName: String,
     var albumName: String,
     var cover: String,
     var radioStationName: String,
     val playedAt: String
)

fun PlayedTrackDataModel.toDataUI() = PlayedTrackModelUI( MbID, name, artistName, albumName, cover, radioStationName,  playedAt)

fun PlayedTrackDataModel.toData() = PlayedTrackDataEntity( null,MbID, name, artistName, albumName, radioStationName, cover, playedAt)
fun PlayedTrackDataEntity.toData() = PlayedTrackDataModel( MbID, name, artistName, albumName, radioStationName, cover, playedAt)
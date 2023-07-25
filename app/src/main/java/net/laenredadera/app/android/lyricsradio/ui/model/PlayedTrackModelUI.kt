package net.laenredadera.app.android.lyricsradio.ui.model

import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import java.util.Date
data class PlayedTrackModelUI(
     val mbId: String,
     val name: String,
     val artistName: String,
     val albumName: String,
     var cover: String,
     val radioStationName: String,
     val playedAt: String
     )

fun PlayedTrackModelUI.toData() = PlayedTrackDataModel( mbId, name, artistName, albumName, cover, radioStationName, playedAt)

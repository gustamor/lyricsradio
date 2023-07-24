package net.laenredadera.app.android.lyricsradio.ui.model

import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import java.util.Date
data class PlayedTrackModelUI(
    private val id: Int,
    private val mbId: String,
    private val name: String,
    private val artistName: String,
    private val albumName: String,
    private var cover: String,
    private val radioStationName: String,
    private val playedAt: String
    )


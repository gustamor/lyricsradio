package net.laenredadera.app.android.lyricsradio.ui.model

import java.util.Date


data class PlayedTrackModelUI(
    private val mbId: String,
    private val name: String,
    private val albumName: String,
    private val artistName: String,
    private val radioStationName: String,
    private val playedAt: Date
    )
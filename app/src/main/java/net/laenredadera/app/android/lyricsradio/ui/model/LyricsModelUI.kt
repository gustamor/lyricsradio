package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.data.services.network.model.LyricsResponse

data class LyricsModelUI (
    val artist: String,
    val title:String,
    val lyrics:String
    )


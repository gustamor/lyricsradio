package net.laenredadera.app.android.lyricsradio.domain.model

import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.data.services.network.model.LyricsResponse
import net.laenredadera.app.android.lyricsradio.ui.model.LyricsModelUI
data class LyricsModel (
    @SerializedName("artist") val artist: String,
    @SerializedName("title") val title:String,
    @SerializedName("lyrics") val lyrics:String
)

fun LyricsResponse.toData() = LyricsModel(artist,title,lyrics)
fun LyricsModel.toData() = LyricsModelUI(artist,title,lyrics)
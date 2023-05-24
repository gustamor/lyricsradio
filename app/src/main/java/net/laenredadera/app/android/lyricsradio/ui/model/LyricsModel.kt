package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.data.services.network.model.LyricsResponse

data class LyricsModel (
    @SerializedName("artist") val artist: String,
    @SerializedName("title") val title:String,
    @SerializedName("lyrics") val lyrics:String
    )

fun LyricsResponse.toData() = LyricsModel(artist,title,lyrics);
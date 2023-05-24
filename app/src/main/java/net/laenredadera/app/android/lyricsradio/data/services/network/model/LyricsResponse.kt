package net.laenredadera.app.android.lyricsradio.data.services.network.model

import com.google.gson.annotations.SerializedName

data class LyricsResponse(
    @SerializedName("artist") val artist: String,
    @SerializedName("title") val title:String,
    @SerializedName("lyrics") val lyrics:String
    )



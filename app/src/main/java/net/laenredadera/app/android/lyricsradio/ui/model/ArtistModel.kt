package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName

data class ArtistModelUI(
    @SerializedName("mbid") val mbid: String,
    @SerializedName("name") val name: Int,
    @SerializedName("cover") val cover: String?,

)

package net.laenredadera.app.android.lyricsradio.data.model

import com.google.gson.annotations.SerializedName

data class RadioStationItem (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("description") val description: String?,
    @SerializedName("address") val address: RadioStationAdresses
    )

data class RadioStationAdresses (
    @SerializedName("urlAddress") val urlAddress: String,
    @SerializedName("mp3u") val mp3u: String?,
    @SerializedName("pls") val pls: String?,
    @SerializedName("xspf") val xspf: String? )


package net.laenredadera.app.android.lyricsradio.data.services.network.model

import com.google.gson.annotations.SerializedName

data class RadioStationItem (
    @SerializedName("id") val id: Int,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("url") val url: String?,
    @SerializedName("description") val description: String,
    @SerializedName("address") val address: RadioStationsAddressResponse
)

data class RadioStationsAddressResponse(
    @SerializedName("icy_url") val icy_url: String,
    @SerializedName("mp3u") val mp3u: String,
    @SerializedName("pls") val pls: String,
    @SerializedName("xspf") val xspf: String)


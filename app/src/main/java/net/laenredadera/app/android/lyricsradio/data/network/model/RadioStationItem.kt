package net.laenredadera.app.android.lyricsradio.data.network.model

import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel

data class RadioStationItem (
    @SerializedName("id") val id: Int,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("url") val url: String?,
    @SerializedName("description") val description: String,
    @SerializedName("address") val address: RadioStationsAddress
)

data class RadioStationsAddress(
    @SerializedName("urlAddress") val url: String,
    @SerializedName("mp3u") val mp3u: String,
    @SerializedName("pls") val pls: String,
    @SerializedName("xspf") val xspf: String)


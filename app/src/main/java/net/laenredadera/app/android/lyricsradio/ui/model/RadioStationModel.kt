package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationsAddress

data class RadioStationModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("url") val url: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("address") val address: RadioStationsAddress
)

data class RadioStationsAddress(
    @SerializedName("urlAddress") val urlAddress: String,
    @SerializedName("mp3u") val mp3u: String?,
    @SerializedName("pls") val pls: String?,
    @SerializedName("xspf") val xspf: String? )

fun RadioStationItem.toData() = RadioStationModel(id,name,cover, url,description!!,address);
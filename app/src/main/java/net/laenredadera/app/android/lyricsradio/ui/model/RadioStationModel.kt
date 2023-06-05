package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName

import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationsAddressResponse

data class RadioStationModel(
    @SerializedName("id") val id: Int,
    @SerializedName("enabled") val enabled: Boolean = true,
    @SerializedName("name") val name: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("url") val url: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("address") val address: RadioStationsAddress
)


data class RadioStationsAddress(
    @SerializedName("url") val icy_url: String,
    @SerializedName("mp3u") val mp3u: String?,
    @SerializedName("pls") val pls: String?,
    @SerializedName("xspf") val xspf: String? )
fun RadioStationsAddressResponse.toData() = RadioStationsAddress(icy_url,mp3u,pls,xspf)
fun RadioStationItem.toData() = RadioStationModel(id,enabled, name,cover, url,description,address.toData());

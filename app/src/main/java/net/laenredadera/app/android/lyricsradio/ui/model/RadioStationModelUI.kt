package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName

import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationsAddressResponse
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationsAddress
import net.laenredadera.app.android.lyricsradio.domain.model.toData

data class RadioStationModelUI(
    val id: Int,
    val enabled: Boolean = true,
    val name: String,
    val cover: String,
    val url: String?,
    val description: String?,
    val address: RadioStationsAddressUI
)


data class RadioStationsAddressUI(

    @SerializedName("url") val icy_url: String,
    val mp3u: String?,
    val pls: String?,
    val xspf: String?
)
fun RadioStationsAddress.toData() = RadioStationsAddressUI(icy_url,mp3u,pls,xspf)
fun RadioStationModel.toData() = RadioStationModelUI(id,enabled, name,cover, url,description,address.toData())
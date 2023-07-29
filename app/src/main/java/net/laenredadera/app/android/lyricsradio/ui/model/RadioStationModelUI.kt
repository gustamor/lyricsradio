package net.laenredadera.app.android.lyricsradio.ui.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationsAddress
import net.laenredadera.app.android.lyricsradio.domain.model.TopStationsModel

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
    @SerializedName("url")
    val icy_url: String,
    val mp3u: String?,
    val pls: String?,
    val xspf: String?
)


data class TopStationUi(
    @PrimaryKey
    var id: Int,
    var enabled: Boolean = true,
    var name: String,
    var address: String,
    var cover: String,
    var description: String,
    var lastTimePlayed: Long?,
    var numTimesPlayed: Int? ,
)

fun RadioStationsAddress.toData() = RadioStationsAddressUI(icy_url,mp3u,pls,xspf)
fun RadioStationModel.toData() = RadioStationModelUI(id,enabled, name,cover, url,description,address.toData())
fun TopStationUi.toRadioStation() = RadioStationModelUI(id,enabled, name,cover,address,description,RadioStationsAddressUI(address,"","",""))
fun TopStationsModel.toData() = TopStationUi(id,enabled, name,cover,address,description,lastTimePlayed,numTimesPlayed)
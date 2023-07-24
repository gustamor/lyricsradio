package net.laenredadera.app.android.lyricsradio.domain.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import net.laenredadera.app.android.lyricsradio.data.db.TopStationEntity
import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItemResponse
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

data class TopStationsModel(
    @PrimaryKey
    var id: Int,
    var enabled: Boolean = true,
    var name: String,
    var cover: String,
    var description: String,
    var lastTimePlayed: Long?,
    var numTimesPlayed: Int? ,
)

fun RadioStationsAddressResponse.toData() = RadioStationsAddress(icy_url,mp3u,pls,xspf)

fun RadioStationItemResponse.toData() = RadioStationModel(id,enabled, name,cover, url,description, address.toData())

fun TopStationEntity.toData() = TopStationsModel(id,enabled, name,cover,description,lastTimePlayed,numTimesPlayed)

fun TopStationsModel.toData() = TopStationEntity(id,enabled, name,cover,description, lastTimePlayed,numTimesPlayed)
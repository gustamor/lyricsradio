package net.laenredadera.app.android.lyricsradio.data.network.model

import com.google.gson.annotations.SerializedName

data class RadioStationsDataResponse(
    @SerializedName("name") val name: String,
    @SerializedName("stations") val stations: List<RadioStationItem>
)
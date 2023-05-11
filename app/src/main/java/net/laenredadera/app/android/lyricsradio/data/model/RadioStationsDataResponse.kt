package net.laenredadera.app.android.lyricsradio.data.model

import com.google.gson.annotations.SerializedName

data class RadioStationsDataResponse(
    @SerializedName("name") val name: String,
    @SerializedName("stations") val stations: List<RadioStationItem>
)
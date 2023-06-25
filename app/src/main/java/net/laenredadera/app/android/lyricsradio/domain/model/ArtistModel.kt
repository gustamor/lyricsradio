package net.laenredadera.app.android.lyricsradio.domain.model

import com.google.gson.annotations.SerializedName

data class ArtistModel(
    @SerializedName("MbID") val MbID: String,
    @SerializedName("name") val name: String,

    )

package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName

data class SongModelUI(
    @SerializedName("id") val ide: Int,
    @SerializedName("artist") val artist: ArtistModelUI,
    @SerializedName("title") val title: String,
    @SerializedName("album") val album: AlbumModelUI
)

data class AlbumModelUI(
    @SerializedName("mbid") val mbid: String,
    @SerializedName("name") val name: Int,
    @SerializedName("cover") val cover: String?,
)


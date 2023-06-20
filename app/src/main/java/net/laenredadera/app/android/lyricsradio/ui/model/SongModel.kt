package net.laenredadera.app.android.lyricsradio.ui.model

import com.google.gson.annotations.SerializedName

data class SongModel(
    @SerializedName("id") val ide: Int,
    @SerializedName("artist") val artist: ArtistModel,
    @SerializedName("title") val title: String,
    @SerializedName("album") val album: AlbumModel
)

data class AlbumModel(
    @SerializedName("mbid") val mbid: String,
    @SerializedName("name") val name: Int,
    @SerializedName("cover") val cover: String?,
)


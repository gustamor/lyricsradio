package net.laenredadera.app.android.lyricsradio.data.services.network.model

import com.google.gson.annotations.SerializedName
data class AlbumInfoResponse(
    @SerializedName("album")
    val album: Album
) {
    data class Album(
        @SerializedName("artist")
        val artist: String,
        @SerializedName("image")
        val image: List<Image>,
        @SerializedName("name")
        val name: String,
        @SerializedName("tracks")
        val tracks: Tracks,
        @SerializedName("url")
        val url: String,
        @SerializedName("wiki")
        val wiki: Wiki
    ) {
        data class Image(
            @SerializedName("size")
            val size: String,
            @SerializedName("#text")
            val text: String
        )

        data class Tracks(
            @SerializedName("track")
            val track: List<Track>
        ) {
            data class Track(
                @SerializedName("artist")
                val artist: Artist,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("name")
                val name: String,

            ) {
                data class Artist(
                    @SerializedName("mbid")
                    val mbid: String,
                    @SerializedName("name")
                    val name: String,
                )

            }
        }


    }
}

data class Wiki(
    @SerializedName("content")
    val content: String,
    @SerializedName("published")
    val published: String,
    @SerializedName("summary")
    val summary: String
)
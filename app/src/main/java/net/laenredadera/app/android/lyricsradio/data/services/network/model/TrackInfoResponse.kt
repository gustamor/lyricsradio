package net.laenredadera.app.android.lyricsradio.data.services.network.model


import com.google.gson.annotations.SerializedName

data class TrackInfoResponse(
    @SerializedName("track")
    val track: Track
) {
    data class Track(
        @SerializedName("album")
        val album: Album,
        @SerializedName("artist")
        val artist: Artist,
        @SerializedName("duration")
        val duration: String,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("wiki")
        val wiki: Wiki
    ) {
        data class Album(
            @SerializedName("artist")
            val artist: String,
            @SerializedName("image")
            val image: List<Image>,
            @SerializedName("mbid")
            val mbid: String,
            @SerializedName("title")
            val title: String,
        ) {
            data class Image(
                @SerializedName("size")
                val size: String,
                @SerializedName("#text")
                val text: String
            )
        }

        data class Artist(
            @SerializedName("mbid")
            val mbid: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )


        data class Wiki(
            @SerializedName("content")
            val content: String,
            @SerializedName("published")
            val published: String,
            @SerializedName("summary")
            val summary: String
        )
    }
}
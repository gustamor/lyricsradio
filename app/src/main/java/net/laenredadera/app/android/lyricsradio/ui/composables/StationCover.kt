package net.laenredadera.app.android.lyricsradio.ui.composables

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import net.laenredadera.app.android.lyricsradio.R


@Composable
fun StationCover(url: String) {
    Box(
        Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .height(64.dp)
            .width(64.dp)
            .testTag("StationCover")
    ) {

        SubcomposeAsyncImage(
            model = url,
            contentDescription = "stationCoverImage",
            contentScale = ContentScale.FillBounds,
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(
                    color = Color.Red, modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            } else if (state is AsyncImagePainter.State.Error || state is AsyncImagePainter.State.Empty) {
                val drawable = AppCompatResources.getDrawable(LocalContext.current, R.drawable.blur)
                Image(
                    painter = rememberDrawablePainter(drawable = drawable),
                    modifier = Modifier.fillMaxSize(), contentDescription = "imagenBlur"
                )
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    }
}

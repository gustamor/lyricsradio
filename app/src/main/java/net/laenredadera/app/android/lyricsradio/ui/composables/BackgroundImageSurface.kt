package net.laenredadera.app.android.lyricsradio.ui.composables



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp


@Composable
fun BackgroundImageSurface(
    modifier: Modifier = Modifier,
    background: Painter,
    content: @Composable () -> Unit,
){
    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize().background(color =Color(0xFF1A1A1A))) {

            Image(
               painter = background,
                contentDescription = "backgroud_imagen",
                contentScale = ContentScale.Crop,
                alpha = 0.3f,
                modifier = Modifier.fillMaxSize( )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 21.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }


    }


}
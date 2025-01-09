package net.laenredadera.app.android.lyricsradio.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SadikSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val gradientColors = listOf(
        Color(0xFF1A1A1A),  // Negro más oscuro arriba
        Color(0xFF2A2A2A)   // Un poco más claro abajo
    )
    val glowColor = Color(0x40FFFFFF)  // Blanco semi-transparente

    Surface(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(21.dp))
        ,
        shadowElevation = 28.dp,  // Sombra guapa
        border = BorderStroke(1.dp, Color.Black),  // Borde negro fino
        shape = RoundedCornerShape(21.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(gradientColors)
                )
                .padding(13.dp)
        ) {
            content()
        }
    }
}

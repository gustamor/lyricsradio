package net.laenredadera.app.android.lyricsradio.ui

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import net.laenredadera.app.android.lyricsradio.R

@Preview
@Composable
fun MainScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
            .background(Color(0xFF1C1C1C))
    ) {
        MainBody()
    }
}

@Composable
fun MainBody() {
    Column(
        Modifier.background(Color(0xFF1C1C1C))

    ) {
        SubHeaderMain("Welcome the destroyer", "Explore")

        Space(8)
        Row(
            Modifier
                .fillMaxWidth()
                .height(128.dp)
                .horizontalScroll(rememberScrollState())
        ) {

            RoundedBordersSquareImage(
                painter = painterResource(id = R.drawable.blur),
                width = 176.dp,
                height = 176.dp,
                "Radio1",
                Modifier
                    .padding(horizontal = 6.dp)
            )
            RoundedBordersSquareImage(
                painter = painterResource(id = R.drawable.blur),
                width = 176.dp,
                height = 176.dp,
                "Metal 2",

                Modifier
                    .padding(horizontal = 6.dp)
            )
            RoundedBordersSquareImage(
                    painter = painterResource(id = R.drawable.blur),
            width = 176.dp,
            height = 176.dp,
            "You e",
            Modifier
                .padding(horizontal = 6.dp)
            )
            RoundedBordersSquareImage(
                painter = painterResource(id = R.drawable.blur),
                width = 176.dp,
                height = 176.dp,
                "You e",
                Modifier
                    .padding(horizontal = 6.dp)
            )
            RoundedBordersSquareImage(
                painter = painterResource(id = R.drawable.blur),
                width = 176.dp,
                height = 176.dp,
                "You e",
                Modifier
                    .padding(horizontal = 6.dp)
            )
            RoundedBordersSquareImage(
                painter = painterResource(id = R.drawable.blur),
                width = 176.dp,
                height = 176.dp,
                "You e",
                Modifier
                    .padding(horizontal = 6.dp)
            )
        }
        Space(16)
        SubHeaderMain("Favs", "View")
        Row(

            Modifier
                .height(160.dp)

        ) {
            RoundedBordersRectangleImage(
                painter = painterResource(id = R.drawable.blur),
                width = 200.dp,
                height = 128.dp,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 6.dp)
            )
            RoundedBordersRectangleImage(
                painter = painterResource(id = R.drawable.blur),
                width = 200.dp,
                height = 128.dp,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 6.dp)
            )
        }
        Space(4)
        TitleHeaderMain("Now Playing", 24)
        Space(4)
        Column(Modifier.verticalScroll(rememberScrollState())) {
            ItemNowPlaying()
            ItemNowPlaying()
            ItemNowPlaying()
            ItemNowPlaying()
        }



    }
}

@Composable
fun TitleHeaderMain(title: String, fontSize: Int) {
    Text(
        text = title, fontSize = fontSize.sp,
        color = Color.LightGray,
        fontWeight = FontWeight.Bold, modifier = Modifier
            .padding(8.dp)
            .testTag(title)
    )
}

@Composable
fun SubHeaderMain(title: String, textButton: String?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        TitleHeaderMain(title, 21)
        if (textButton != null) {

            Button(
                onClick = { /* Your onClick code here */ },
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .height(32.dp)
                    .padding(end = 16.dp),
            ) {
                Text(text = textButton)
            }
        }
    }
}

@Composable
fun RoundedBordersSquareImage(
    painter: Painter,
    width: Dp,
    height: Dp,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {

    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,

        modifier = modifier
            .aspectRatio(1f)
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(32.dp))
    )
}


@Composable
fun RoundedBordersRectangleImage(
    painter: Painter,
    width: Dp,
    height: Dp,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val w = (((screenWidth)-(screenWidth/10))/2f)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,
         modifier = modifier
             .height(height)
             .width(w.dp)
             .clip(RoundedCornerShape(32.dp))
    )
}


@Composable
fun ItemNowPlaying() {

    Box(
        modifier = Modifier
            .shadow(4.dp)
            .testTag("ItemCard")
            .background(Color(0xFF1C1C1C))
            .clickable { /*TODO */ }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(92.dp)
                .padding(8.dp)
                .background(Color(0xFF1C1C1C)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        )
        {
            Row(
                Modifier
                    .height(92.dp)
                    .padding(4.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {
                val blur = AppCompatResources.getDrawable(LocalContext.current, R.drawable.blur)
                Image(
                    painter = rememberDrawablePainter(drawable = blur),
                    contentDescription = "nowPlayingCoverImage",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                )


                Column(Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "Artist name",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.testTag("TextItemNowplayingArtist")
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = "Song title ",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.testTag("TextItemNowplayingSong")
                    )
                }
            }
            Box(
                Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable { /*TODO*/ }
                    .testTag("MenuHorizontalItem")
            ) {
                val drawable = AppCompatResources.getDrawable(
                    LocalContext.current,
                    androidx.media3.ui.R.drawable.exo_icon_play
                )
                Image(
                    painter = rememberDrawablePainter(drawable = drawable),
                    contentDescription = "MenuHorizImage",
                )
            }
        }
    }
}

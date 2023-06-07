package net.laenredadera.app.android.lyricsradio

sealed class Routes (val route: String){
    object PlayerScreen:Routes("playerScreen")
    object HomeScreen:Routes("homeScreen")
}
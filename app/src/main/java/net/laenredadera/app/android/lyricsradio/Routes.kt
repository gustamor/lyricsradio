package net.laenredadera.app.android.lyricsradio

sealed class Routes (val route: String){
    data object PlayerScreen:Routes("playerScreen")
    data object HomeScreen:Routes("homeScreen")
    data object MainScreen:Routes("mainScreen")
    data object PreviouslyPlayedScreen:Routes("previouslyPlayedScreen")


}
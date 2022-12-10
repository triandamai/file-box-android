package app.trian.filebox.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.ui.theme.FileBoxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenSignUp(
    goToSingIn:()->Unit={},
){
    Column {
        TextField(value = "", onValueChange = {})
    }
}

@Preview
@Composable
fun PreviewScreenSignIn() {
    FileBoxTheme {
        ScreenSignUp()
    }
}
package app.trian.filebox.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer
import app.trian.filebox.components.AnnotationTextItem
import app.trian.filebox.components.TextWithAction
import app.trian.filebox.feature.signin.ScreenSignIn


@Composable
internal fun ScreenHome(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home")
    }
}

@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseContainer {
        ScreenHome()
    }
}
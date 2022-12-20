package app.trian.filebox.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.BaseContainer
import app.trian.filebox.ui.theme.FileBoxTheme

@Composable
fun BottomBarSelectFile(
    message: String = "",
    onDetailClicked: () -> Unit = {},
    onShareClicked: () -> Unit = {}
) {
    BottomAppBar(
        actions = {
            Text(
                text = message,
                modifier = Modifier.clickable {
                    onDetailClicked()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onShareClicked) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = "")
            }
        }
    )


}

@Preview
@Composable
fun PreviewBottomBarSelectFile() {
    FileBoxTheme {
        BaseContainer(
            bottomBar = {
                BottomBarSelectFile()
            }
        )
    }
}
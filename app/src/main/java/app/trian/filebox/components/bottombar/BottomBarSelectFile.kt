package app.trian.filebox.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.ui.theme.FileBoxTheme

@Composable
fun BottomBarSelectFile(
    message: String = "",
    onDetailClicked: () -> Unit = {},
    onShareClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = onDetailClicked) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onDeleteClicked) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
            }
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
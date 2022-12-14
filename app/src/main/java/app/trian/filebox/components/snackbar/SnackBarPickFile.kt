/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.40
 */
package app.trian.filebox.components.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackBarPickFile(
    message: String = "",
    onMoreClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    Snackbar(
        modifier = Modifier.padding(
            horizontal = 10.dp,
            vertical = 8.dp
        ),
        actionOnNewLine = false,
        shape = SnackbarDefaults.shape,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        actionContentColor = MaterialTheme.colorScheme.primary,
        dismissActionContentColor = MaterialTheme.colorScheme.onSurface,
        dismissAction = {
            IconButton(onClick = onMoreClick) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "")
            }
        },
        action = {
            Text(
                text = "Send",
                modifier = Modifier.clickable {
                    onActionClick()
                }
            )
        }
    ) {
        Text(text = message)
    }
}


/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.47
 */
package app.trian.filebox.feature.homeSend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.base.BaseContainer

@Composable
fun BottomSheetOption(
    onViewList:()->Unit={}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp,
                    horizontal = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "1 Selected",
                color = MaterialTheme.colorScheme.onSurface
            )
            TextButton(onClick = onViewList) {
                Text(text = "View List")
            }
        }
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        ItemBottomSheetOption(text = "Send files", icon = Icons.Outlined.Send, onClick = {})
        ItemBottomSheetOption(text = "Share link", icon = Icons.Outlined.Link, onClick = {})
        ItemBottomSheetOption(text = "Copy", icon = Icons.Outlined.CopyAll, onClick = {})
        ItemBottomSheetOption(text = "Move", icon = Icons.Outlined.FileCopy, onClick = {})
        ItemBottomSheetOption(text = "Delete", icon = Icons.Outlined.Delete, onClick = {})
        ItemBottomSheetOption(text = "Clear Selection", icon = Icons.Outlined.Clear, onClick = {})

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemBottomSheetOption(
    text: String = "",
    icon: ImageVector = Icons.Outlined.Send,
    onClick: () -> Unit = {}
) {
    ListItem(
        modifier = Modifier.clickable { },
        headlineText = {
            Text(text = text)
        },
        leadingContent = {
            Icon(imageVector = icon, contentDescription = "")
        },

        )
}

@Preview
@Composable
fun PreviewBottomSheetOption() {
    BaseContainer {
        BottomSheetOption()
    }
}
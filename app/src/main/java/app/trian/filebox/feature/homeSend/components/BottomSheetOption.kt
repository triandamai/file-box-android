package app.trian.filebox.feature.homeSend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.base.BaseContainer

@Composable
fun BottomSheetOption() {
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
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "View List")
            }
        }
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        ItemBottomSheetOption("Send files")
        ItemBottomSheetOption("Share link")
        ItemBottomSheetOption("Copy")
        ItemBottomSheetOption("Move")
        ItemBottomSheetOption("Delete")
        ItemBottomSheetOption("Clear Selection")

    }
}

@Composable
fun ItemBottomSheetOption(
    text:String=""
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.height(4.dp))
            Icon(
                modifier=Modifier.padding(
                    all = 16.dp
                ),
                imageVector = Icons.Outlined.Link,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface
            )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = text, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetOption() {
    BaseContainer {
        BottomSheetOption()
    }
}
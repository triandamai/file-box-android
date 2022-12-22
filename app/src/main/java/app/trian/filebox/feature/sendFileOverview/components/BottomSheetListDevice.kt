package app.trian.filebox.feature.sendFileOverview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.base.BaseContainer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetListDevice(
    isExpanded: Boolean = false,
    onExpandClick: () -> Unit = {}
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant
            ),
        content = {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 6.dp,
                            horizontal = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Device",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    IconButton(onClick = { onExpandClick() }) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            items(
                listOf("sasa", "sas")
            ) {
                ListItem(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant),
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    headlineText = {
                        Text(text = "Macbook Pro", color = MaterialTheme.colorScheme.primary)
                    },
                    supportingText = {
                        Text(text = "Macbook Pro", color = MaterialTheme.colorScheme.primary)
                    }
                )
            }
        })

}


@Preview
@Composable
fun PreviewBottomSheetListDevice() {
    BaseContainer {
        BottomSheetListDevice()
    }
}
package app.trian.filebox.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer

@Composable
fun CardItemFile(
    name:String
) {
    Card(
        colors = CardDefaults.outlinedCardColors(
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .height(70.dp)
    ) {
        Column(
            Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    "File",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    name,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardItemFile() {
    BaseContainer {
        LazyVerticalGrid(
            contentPadding = PaddingValues(
                horizontal = 8.dp
            ),
            columns = GridCells.Fixed(count = 2),
            content = {
                items(1) {
                    CardItemFile(
                        name = ""
                    )
                }
            })
    }
}
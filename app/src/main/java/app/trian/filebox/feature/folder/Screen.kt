package app.trian.filebox.feature.folder


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer
import app.trian.filebox.components.CardItemFolder


@Composable
internal fun ScreenFolder(
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        )
    ) {
        items(
            listOf("sa", "sas", "sas", "sasa", "sasa", "sasa", "fejds"),
        ) {
            CardItemFolder()
        }
    }
}

@Preview
@Composable
fun PreviewScreenFolder() {
    BaseContainer {
        ScreenFolder()
    }
}
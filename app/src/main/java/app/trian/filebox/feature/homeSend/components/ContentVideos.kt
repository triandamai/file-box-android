package app.trian.filebox.feature.homeSend.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.BaseContainer
import app.trian.filebox.components.CardItemImage
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.composables.gridItems
import app.trian.filebox.data.datasource.local.videos.VideosFile
import app.trian.filebox.data.models.DataState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentVideos(
    modifier: Modifier = Modifier,
    data: DataState<Map<String, List<VideosFile>>> = DataState.Loading,
    onItemSelected: (VideosFile) -> Unit = {}
) {
    when (data) {
        is DataState.Data -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                content = {
                    data.data.forEach { (group, fileModels) ->
                        stickyHeader {
                            Text(
                                text = group,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        gridItems(fileModels, columnCount = 4) { file ->
                            CardItemImage(
                                name = file.name,
                                id = file.uid
                            )
                        }
                    }
                })

        }
        DataState.Empty -> EmptyScreen()
        is DataState.Error -> Unit
        DataState.Loading -> LoadingScreen()
    }

}

@Preview
@Composable
fun PreviewContentVideos() {
    BaseContainer {
        ContentVideos(
            data = DataState.Loading
        )
    }
}
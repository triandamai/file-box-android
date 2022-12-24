/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.47
 */
package app.trian.filebox.feature.homeSend.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.composables.gridItems
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.models.FileModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentDocuments(
    modifier: Modifier = Modifier,
    data: DataState<Map<String, List<FileModel>>> = DataState.Loading,
    onItemSelected: (SelectedFile, Boolean) -> Unit = { _, _ -> }
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
                                id = file.id
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
fun PreviewContentDocuments() {
    BaseContainer {
        ContentDocuments(
            data = DataState.Loading
        )
    }
}
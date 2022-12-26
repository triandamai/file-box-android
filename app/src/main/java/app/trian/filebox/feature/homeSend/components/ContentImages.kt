/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.47
 */
package app.trian.filebox.feature.homeSend.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.composables.header
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.models.FileModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentImages(
    modifier: Modifier = Modifier,
    data: DataState<Map<String, List<FileModel>>> = DataState.Loading,
    selectedFile: List<Long> = listOf(),
    onItemSelected: (SelectedFile, Boolean) -> Unit = { _, _ -> },
) {


    when (data) {
        is DataState.Data -> {
            LazyVerticalGrid(columns = GridCells.Fixed(4), content = {
                data.data.forEach { (group, files) ->
                    header {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 4.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = group,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                    items(files, key = { it.id }) { file ->
                        CardItemImage(
                            name = file.name,
                            id = file.id,
                            selected = {
                                file.id in selectedFile
                            },
                            onClick = {
                                val exist = file.id in selectedFile
                                onItemSelected(
                                    SelectedFile(
                                        uid = file.id,
                                        name = file.name,
                                        size = file.size,
                                        date = file.date,
                                        uri = file.uri.toString(),
                                        path = file.path,
                                        mime = file.mime
                                    ), exist
                                )
                            }
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
fun PreviewContentImages() {
    BaseContainer {
        ContentImages(
            data = DataState.Data(
                mapOf(
                    "Internal" to listOf(
                        FileModel(
                            id = 0,
                            name = "bobo",
                        ),
                        FileModel(
                            id = 0,
                            name = "bobo",
                        ),
                        FileModel(
                            id = 0,
                            name = "bobo",
                        ),
                        FileModel(
                            id = 0,
                            name = "bobo",
                        ),
                        FileModel(
                            id = 0,
                            name = "bobo",
                        )
                    )
                )
            )
        )
    }
}
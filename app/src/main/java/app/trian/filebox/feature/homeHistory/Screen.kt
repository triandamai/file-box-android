package app.trian.filebox.feature.homeHistory

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.feature.homeSend.components.CardItemImage
import app.trian.filebox.composables.gridItems
import app.trian.filebox.data.models.FileModel

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun ScreenHomeHistory(
    modifier: Modifier = Modifier,
    allFiles: Map<String, List<FileModel>> = mapOf(),
    title: List<String> = listOf()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        content = {
//            stickyHeader {
//                TabRow(selectedTabIndex = 0) {
//                    title.forEachIndexed { index, title ->
//                        Tab(
//                            selected = 0 == index,
//                            onClick = {  },
//                            text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
//                        )
//                    }
//                }
//            }
            allFiles.forEach { (group, fileModels) ->
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

@Preview
@Composable
fun PreviewScreenHomeHistory() {
    BaseContainer {
        ScreenHomeHistory(
            title = listOf("All", "Images", "Audio", "Video", "Other")
        )
    }
}
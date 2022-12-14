package app.trian.filebox.feature.homeSend

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.BaseContainer
import app.trian.filebox.composables.gridItems
import app.trian.filebox.data.models.FileModel

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun ScreenHomeSend(
    modifier: Modifier = Modifier,
    allFiles: Map<String, List<FileModel>> = mapOf()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        content = {
            allFiles.forEach { (group, fileModels) ->
                stickyHeader {
                    Text(text = group)
                }
                gridItems(fileModels, columnCount = 4) { file ->
                    Text(text = file.name)
                }
            }
        })
}

@Preview
@Composable
fun PreviewScreenHomeSend() {
    BaseContainer {
        ScreenHomeSend()
    }
}
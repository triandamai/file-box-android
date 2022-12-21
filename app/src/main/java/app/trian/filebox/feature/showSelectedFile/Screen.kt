package app.trian.filebox.feature.showSelectedFile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.models.DataState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenShowSelectedFile(
    data: DataState<List<SelectedFile>> = DataState.Loading
) {
    when (data) {
        is DataState.Data -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                items(data.data) {
                    ListItem(
                        leadingContent = {},
                        headlineText = {
                            Text(text = it.name)
                        },

                        )
                }
            })
        }
        DataState.Empty -> {EmptyScreen()}
        is DataState.Error -> {}
        DataState.Loading -> {
            LoadingScreen()
        }
    }
}

@Preview
@Composable
fun PreviewShowSelectedFile() {
    BaseContainer {
        ScreenShowSelectedFile()
    }
}
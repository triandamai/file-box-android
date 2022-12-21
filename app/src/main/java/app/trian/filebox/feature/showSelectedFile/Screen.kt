package app.trian.filebox.feature.showSelectedFile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.models.DataState

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
                        CardItemSelectedFile(
                            id = it.uid,
                            name = it.name
                        )
                    }
                })
        }
        DataState.Empty -> {
            EmptyScreen()
        }
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
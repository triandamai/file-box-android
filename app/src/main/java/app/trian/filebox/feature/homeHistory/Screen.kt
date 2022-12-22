package app.trian.filebox.feature.homeHistory

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.models.FileModel

@Composable
internal fun ScreenHomeHistory(
    modifier: Modifier = Modifier,
    data: DataState<List<FileModel>> = DataState.Empty,
) {
    when (data) {
        is DataState.Data -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                content = {

                })
        }
        DataState.Empty -> EmptyScreen()
        is DataState.Error -> EmptyScreen()
        DataState.Loading -> LoadingScreen()
    }
}

@Preview
@Composable
fun PreviewScreenHomeHistory() {
    BaseContainer {
        ScreenHomeHistory()
    }
}
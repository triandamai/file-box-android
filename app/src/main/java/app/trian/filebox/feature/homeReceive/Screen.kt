/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.46
 */
package app.trian.filebox.feature.homeReceive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.components.EmptyScreen
import app.trian.filebox.components.LoadingScreen
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.models.FileModel

@Composable
internal fun ScreenHomeReceive(
    modifier: Modifier = Modifier,
    data: DataState<List<FileModel>> = DataState.Empty
) {
    Box(modifier = Modifier.fillMaxSize()) {
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
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Icon(imageVector = Icons.Outlined.QrCode, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun PreviewScreenHomeReceive() {
    BaseContainer {
        ScreenHomeReceive(
        )
    }
}
package app.trian.filebox.feature.showSelectedFile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.base.BaseContainer


@Composable
internal fun ScreenShowSelectedFile(
    modifier: Modifier = Modifier,
) {
    LazyColumn(content = {

    })
}

@Preview
@Composable
fun PreviewShowSelectedFile() {
    BaseContainer {
        ScreenShowSelectedFile()
    }
}
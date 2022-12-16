package app.trian.filebox.feature.homeSend.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.filebox.BaseContainer
import app.trian.filebox.data.datasource.local.images.ImagesFile

@Composable
fun ContentImages(
    images:Map<String,List<ImagesFile>> = mapOf()
) {

}

@Preview
@Composable
fun PreviewContetnImages() {
    BaseContainer {
        ContentImages(
            images = mapOf("" to listOf())
        )
    }
}
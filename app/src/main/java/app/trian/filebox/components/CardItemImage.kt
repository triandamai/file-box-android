package app.trian.filebox.components

import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer

@Composable
fun CardItemImage(
    name: String = "",
    id: Long=0,
    selected:Boolean=false,
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val thumb = try {
        val contentUri = ContentUris.withAppendedId(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            val widthSize = 480
            val heightSize = 480
            val loadThumbnail =
                ctx.contentResolver.loadThumbnail(contentUri, Size(widthSize, heightSize), null)
            loadThumbnail
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
    Box() {
        Column(
            modifier = Modifier.padding(
                4.dp
            )
        ) {
            if (thumb != null) {
                Image(bitmap = thumb.asImageBitmap(), contentDescription = name)
            }
        }
        RadioButton(
            modifier = Modifier.align(Alignment.TopStart),
            selected = selected,
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun PreviewCardItemFile() {
    BaseContainer {
        LazyVerticalGrid(
            contentPadding = PaddingValues(
                horizontal = 8.dp
            ),
            columns = GridCells.Fixed(count = 2),
            content = {
                items(1) {
                    CardItemImage(
                        name = "",
                        0
                    )
                }
            })
    }
}
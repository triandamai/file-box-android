/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.47
 */
package app.trian.filebox.feature.homeSend.components

import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.R
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.composables.gridItems

@Composable
fun CardItemImage(
    name: String = "",
    id: Long = 0,
    selected: () -> Boolean = { false },
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
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
    Box(
        modifier = Modifier.size(
            (screenWidth/4) - 5.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    4.dp
                )
                .fillMaxSize()
        ) {
            if (thumb != null) {
                Image(
                    bitmap = thumb.asImageBitmap(),
                    contentDescription = name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentDescription = name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        RadioButton(
            modifier = Modifier.align(Alignment.TopStart),
            selected = selected(),
            onClick = onClick,
            enabled = true,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = Color.LightGray
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewCardItemFile() {
    BaseContainer {
        LazyColumn(content = {
            stickyHeader {
                Text(text = "Whatsapp Images")
            }
            gridItems(count = 5, columnCount = 4) {
                CardItemImage()
            }
        })
    }
}
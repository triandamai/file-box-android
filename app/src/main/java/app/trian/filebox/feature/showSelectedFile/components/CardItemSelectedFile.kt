/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.48
 */
package app.trian.filebox.feature.showSelectedFile.components

import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.R
import app.trian.filebox.base.BaseContainer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CardItemSelectedFile(
    name: String = "",
    mime: String = "",
    id: Long = 0,
    showSelection: Boolean = false,
    onLongClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    var checked by remember {
        mutableStateOf(false)
    }
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
    ListItem(
        modifier = Modifier.combinedClickable(
            onClick = {},
            onLongClick = onLongClick
        ),
        leadingContent = {
            Row {
                if (showSelection) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            checked = !checked
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
                if (thumb != null) {
                    Image(
                        bitmap = thumb.asImageBitmap(),
                        contentDescription = name,
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.FillBounds
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.image_placeholder),
                        contentDescription = name,
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        },
        headlineText = {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        supportingText = {
            Text(
                text = mime,
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        overlineText = {
            Text(
                text = mime,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    )
}

@Preview
@Composable
fun PreviewCardItemSelectedfile() {
    BaseContainer {
        CardItemSelectedFile()
    }
}
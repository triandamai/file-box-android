package app.trian.filebox.feature.homeSend

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer
import app.trian.filebox.composables.customTabIndicatorOffset
import app.trian.filebox.data.datasource.local.audio.AudioFile
import app.trian.filebox.data.datasource.local.documents.DocumentFile
import app.trian.filebox.data.datasource.local.images.ImageFile
import app.trian.filebox.data.datasource.local.videos.VideosFile
import app.trian.filebox.data.models.DataState
import app.trian.filebox.feature.homeSend.components.ContentAudios
import app.trian.filebox.feature.homeSend.components.ContentDocuments
import app.trian.filebox.feature.homeSend.components.ContentImages
import app.trian.filebox.feature.homeSend.components.ContentVideos
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("NewApi")
@OptIn(
    ExperimentalPagerApi::class
)
@Composable
internal fun ScreenHomeSend(
    images: DataState<Map<String, List<ImageFile>>> = DataState.Empty,
    videos: DataState<Map<String, List<VideosFile>>> = DataState.Empty,
    audios: DataState<Map<String, List<AudioFile>>> = DataState.Empty,
    documents: DataState<Map<String, List<DocumentFile>>> = DataState.Empty,
) {
    val tabs = listOf("PHOTOS", "VIDEOS", "AUDIO", "APPS", "CONTACT", "FILES")
    var selectedTab by remember {
        mutableStateOf(0)
    }
    val pagerState = rememberPagerState(
        initialPage = selectedTab
    )
    val scope = rememberCoroutineScope()
    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedTab = page
        }
    }
    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier.customTabIndicatorOffset(
                        it[selectedTab],
                        6.dp
                    )
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                            selectedTab = index
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Visible
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            count = tabs.size
        ) {
            when (tabs[selectedTab]) {
                "PHOTOS" -> ContentImages(
                    data = images
                )

                "VIDEOS" -> ContentVideos(
                    data = videos
                )

                "AUDIO" -> ContentAudios(
                    data = audios
                )
                "FILES" -> ContentDocuments(
                    data = documents
                )
                else -> {}
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenHomeSend() {
    BaseContainer {
        ScreenHomeSend(
            images = DataState.Loading
        )
    }
}
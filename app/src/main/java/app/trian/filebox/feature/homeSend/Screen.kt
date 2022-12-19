package app.trian.filebox.feature.homeSend

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
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
import app.trian.filebox.components.CardItemFile
import app.trian.filebox.composables.customTabIndicatorOffset
import app.trian.filebox.composables.gridItems
import app.trian.filebox.data.datasource.local.images.ImageFile
import app.trian.filebox.data.models.DataState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("NewApi")
@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class
)
@Composable
internal fun ScreenHomeSend(
    modifier: Modifier = Modifier,
    images: DataState<Map<String, List<ImageFile>>> = DataState.Empty
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
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                content = {
                    when(images){
                        is DataState.Data -> {
                            images.data.forEach { (group, fileModels) ->
                                stickyHeader {
                                    Text(
                                        text = group,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                                gridItems(fileModels, columnCount = 4) { file ->
                                    CardItemFile(
                                        name = file.name
                                    )
                                }
                            }
                        }
                        DataState.Empty -> Unit
                        is DataState.Error -> Unit
                        DataState.Loading -> Unit
                    }
                })
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
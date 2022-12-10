package app.trian.filebox.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.profile.Profile

@Composable
fun FileBoxBottomNavigation(
    items:List<FileBoxBottomNavigation> = listOf(),
    currentRoute:String=Home.routeName,
    onItemClick:(String)->Unit={},
    onFabClick:()->Unit={}
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        actions = {
            items.forEach  {
                IconButton(onClick = { onItemClick(it.routeName) }) {
                    Icon(
                        it.icon,
                        contentDescription = "Menu ${it.name}",
                        tint = if(currentRoute == it.routeName) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                elevation = FloatingActionButtonDefaults.loweredElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Icon(
                    Icons.Outlined.Upload,
                    contentDescription = "Upload"
                )
            }
        }
    )
}

sealed class FileBoxBottomNavigation(
    var routeName: String = "",
    var name: String = "",
    var icon: ImageVector = Icons.Outlined.Home
) {
    class Home() : FileBoxBottomNavigation(
        routeName = app.trian.filebox.feature.home.Home.routeName ,
        name = "Home",
        icon = Icons.Outlined.Home
    )

    class Folder() : FileBoxBottomNavigation(
        routeName = app.trian.filebox.feature.folder.Folder.routeName,
        name = "Folder",
        icon = Icons.Outlined.Folder
    )

    class Profile() : FileBoxBottomNavigation(
        routeName = app.trian.filebox.feature.profile.Profile.routeName,
        name = "Profile",
        icon = Icons.Outlined.Person
    )
}

@Preview
@Composable
fun PreviewBottomNav() {
    BaseContainer(
        bottomBar = {
            FileBoxBottomNavigation(
                items = listOf(
                    FileBoxBottomNavigation.Home(),
                    FileBoxBottomNavigation.Folder(),
                    FileBoxBottomNavigation.Profile()
                )
            )
        }
    ) {

    }
}
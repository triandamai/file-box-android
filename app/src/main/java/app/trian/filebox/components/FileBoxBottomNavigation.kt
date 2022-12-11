package app.trian.filebox.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer
import app.trian.filebox.feature.home.Home

@Composable
fun FileBoxBottomNavigation(
    items: List<FileBoxBottomNavigation> = listOf(),
    currentRoute: String = Home.routeName,
    onItemClick: (String) -> Unit = {}
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        items.forEach {
            NavigationBarItem(
                selected = currentRoute == it.routeName,
                onClick = { onItemClick(it.routeName) },
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = "Menu ${it.name}",
                        tint = if (currentRoute == it.routeName) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
                    )
                }
            )
        }
    }

}

@Composable
fun FileBoxNavigationRail(
    items: List<FileBoxBottomNavigation> = listOf(),
    currentRoute: String = Home.routeName,
    onItemClick: (String) -> Unit = {}
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach {
            NavigationRailItem(
                selected = it.routeName == currentRoute,
                onClick = { onItemClick(it.routeName) },
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = "Menu ${it.name}",
                        tint = if (currentRoute == it.routeName) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
                    )
                })
        }
    }
}

@Composable
fun FileBoxBottomBar(
    items: List<FileBoxBottomNavigation> = listOf(),
    currentRoute: String = Home.routeName,
    onItemClick: (String) -> Unit = {},
    onFabClick: () -> Unit = {}
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        actions = {
            items.forEach {
                IconButton(onClick = { onItemClick(it.routeName) }) {
                    Icon(
                        it.icon,
                        contentDescription = "Menu ${it.name}",
                        tint = if (currentRoute == it.routeName) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
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
        routeName = app.trian.filebox.feature.home.Home.routeName,
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
fun PreviewBottomAppBarNav() {
    BaseContainer(
        bottomBar = {
            FileBoxBottomBar(
                items = listOf(
                    FileBoxBottomNavigation.Home(),
                    FileBoxBottomNavigation.Folder(),
                    FileBoxBottomNavigation.Profile()
                )
            )
        }
    )
}

@Preview
@Composable
fun PreviewBottomNavigation() {
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
    )
}

@Preview(
    widthDp = 700,
    heightDp = 400
)
@Composable
fun PreviewBottomNavigationRail() {
    BaseContainer() {
        Row(modifier = Modifier.fillMaxSize()) {
            FileBoxNavigationRail(
                items = listOf(
                    FileBoxBottomNavigation.Home(),
                    FileBoxBottomNavigation.Folder(),
                    FileBoxBottomNavigation.Profile()
                )
            )
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "hai", color = Color.Blue)
            }
        }
    }
}
package ke.hub.composeanimations.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ke.hub.composeanimations.ui.screens.DialScreen
import ke.hub.composeanimations.ui.screens.HomeScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val topLevelBackStack = remember { TopLevelBackStack<NavKey>(Route.Home) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            topLevelBackStack.removeLastOrNull()
                            drawerState.close()
                        }
                    },
                )
                NavigationDrawerItem(
                    label = { Text(text = "Dial") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            topLevelBackStack.add(Route.Dial)
                        }
                    },
                )
                // ...other drawer items
            }
        },
        drawerState = drawerState,
    ) {
        // Screen content

        Scaffold(
            modifier = modifier.fillMaxSize(),
        ) { innerPadding ->
            NavDisplay(
                backStack = topLevelBackStack.backStack,
                modifier = Modifier.padding(innerPadding.calculateBottomPadding()),
                onBack = { topLevelBackStack.removeLastOrNull() },
                entryProvider =
                    entryProvider {
//                Home Screen
                        entry<Route.Home> {
                            HomeScreen()
                        }

                        entry<Route.Dial> {
                            DialScreen()
                        }
                    },
            )
        }
    }
}

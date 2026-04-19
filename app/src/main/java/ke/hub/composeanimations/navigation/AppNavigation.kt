package ke.hub.composeanimations.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ke.hub.composeanimations.ui.screens.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val topLevelBackStack = remember { TopLevelBackStack<NavKey>(Route.Home) }

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
                },
        )
    }
}

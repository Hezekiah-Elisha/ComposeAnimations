package ke.hub.composeanimations.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
//    @Serializable
    @Serializable
    data object Home : Route

    @Serializable
    data object Dial : NavKey
}

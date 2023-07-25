package uk.adbsalam.portfolio.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.google.gson.Gson

/**
 * @param route route to navigate to
 * @param root root as where to pop up to
 *
 * this wil navigate to fragment and set root as first fragment,
 * All fragments will be removed from backstack and only root will stay as first
 */
fun NavController.navigateAndResetHome(route: NavigationScreen, root: NavigationScreen) {
    this.navigate(route.route()) {
        popUpTo(root.route()) {
            inclusive = false
        }
    }
}

/**
 * navigate to a route using route name
 */
fun NavController.toDestination(route: NavigationScreen) {
    this.navigate(route.route())
}

/**
 * remove all backstack entries and navigate to route as first
 */
fun NavController.popToRoute(route: NavigationScreen) {
    this.popBackStack(route.route(), false)
}

/**
 * navigate using args as object. Preferred Data class
 * use @Keep annotations on args dataclass to avoid obfuscation issues
 */
fun NavController.toDestination(route: NavigationScreen, item: Any) {
    this.navigate("${route.name}/${item.mapObjectToParcelableData()}")
}

/**
 * map name of fragment to navigation route format
 * which is "routeName/{argsName}"
 */
fun NavigationScreen.route(): String {
    return "${this.name}/{${this.name}}"
}

/**
 * create routes and args names, return composable to add to nav graph
 */
fun NavGraphBuilder.composeRoute(route: NavigationScreen, screen: @Composable() () -> Unit) {
    return composable(
        route.route(),
        arguments = route.args()
    ) {
        screen()
    }
}

/**
 * Add args to compose using arg name, return a list of nav args
 */
fun NavigationScreen.args(): List<NamedNavArgument> {
    return listOf(
        navArgument(this.name) { type = NavType.StringType }
    )
}

/**
 * convert args object to Serializable Json so it can be passed as nav arg
 */
fun Any.mapObjectToParcelableData(): String {
    return try {
        return Uri.encode(Gson().toJson(this).replace("%", "%25"))
    } catch (e: java.lang.Exception) {
        ""
    }
}

/**
 * Extract nav args from compose backstack entry. This is then mapped to
 * Inline Type passed
 * example usage
 * navController.backStackEntry.arguments.extractArgs<HomeScreenArgs>()
 */
inline fun <reified T : Any> NavController.navArgs(): T? {
    val key = this.currentBackStackEntry?.destination?.arguments?.keys?.first().orEmpty() //arg name
    val args = this.currentBackStackEntry?.arguments?.getString(key) ?: return null
    return try {
        Gson().fromJson(Uri.decode(args), T::class.java)
    } catch (e: java.lang.Exception) {
        null
    }
}
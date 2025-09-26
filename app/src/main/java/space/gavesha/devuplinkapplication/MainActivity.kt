package space.gavesha.devuplinkapplication

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import space.gavesha.devuplinkapplication.ui.common.EmptyScreen
import space.gavesha.devuplinkapplication.ui.common.search.TitleSearchBar
import space.gavesha.devuplinkapplication.ui.common.search.TitleSearchBarUiState
import space.gavesha.devuplinkapplication.ui.contacts.ContactScreen
import space.gavesha.devuplinkapplication.ui.navigation.Destination
import space.gavesha.devuplinkapplication.ui.theme.AppTheme
import space.gavesha.devuplinkapplication.ui.theme.DevUpLinkApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set orientation based on device type
        // only landscape available on tablet devices
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        enableEdgeToEdge()
        setContent {
            DevUpLinkApplicationTheme {
                BottomNavigationBar()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.CONTACTS
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedDestination = remember(currentRoute) {
        Destination.entries.find { it.route == currentRoute } ?: startDestination
    }

    val currentTitle = stringResource(startDestination.labelRes)

    // searchState should collected from the viewmodel
    // search functionality not implemented
    var searchState by remember { mutableStateOf(TitleSearchBarUiState(currentTitle)) }

    LaunchedEffect(currentRoute) {
        searchState = searchState.copy(title = currentTitle)
        // should update tile using viewmodel and searchState should collected from the viewmodel
    }

    Scaffold(
        topBar = {
            TitleSearchBar(state = searchState, modifier = modifier)
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = currentRoute == Destination.CONTACTS.route,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 }
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { it / 2 }
                ) + fadeOut()
            ) {
                ExtendedFloatingActionButton(
                    containerColor = AppTheme.colors.primaryContainer,
                    contentColor = AppTheme.colors.onPrimaryContainer,
                    shape = RoundedCornerShape(percent = 50),
                    onClick = {
                        //  Connect to add contact action
                        //  action can be vary depend on the other screens
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.cd_add_new_contact)
                        )
                    },
                    text = { Text(text = stringResource(R.string.contact)) },
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = AppTheme.colors.bottomNavSurface,
                contentColor = AppTheme.colors.onSurfaceVariant,
                windowInsets = WindowInsets.navigationBars
                    .union(WindowInsets.displayCutout)
                    .only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal)
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    val isSelected = selectedDestination == destination
                    val icon = if (isSelected) {
                        destination.selectedIcon
                    } else {
                        destination.defaultIcon
                    }
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(route = destination.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = stringResource(destination.contentDescriptionRes)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(destination.labelRes),
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Visible
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AppTheme.colors.selectedNavItemColor,
                            selectedTextColor = AppTheme.colors.selectedNavItemColor,
                            indicatorColor = AppTheme.colors.selectedNavItemBackground,
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController,
            startDestination,
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
    }

}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route,
        modifier = modifier
            .fillMaxHeight()
            .background(AppTheme.colors.tabBackground)
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.CONTACTS -> ContactScreen(modifier)
                    Destination.MESSAGES,
                    Destination.CALLING,
                    Destination.NOTIFICATION,
                    Destination.MORE -> EmptyScreen(label = stringResource(destination.contentDescriptionRes))
                }
            }
        }
    }
}


@Preview(
    showBackground = true, showSystemUi = true,
    device = Devices.PIXEL_8A,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainPreview() {
    DevUpLinkApplicationTheme {
        BottomNavigationBar()
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = Devices.PIXEL_8A,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun LightThemePreview() {
    DevUpLinkApplicationTheme {
        BottomNavigationBar()
    }
}

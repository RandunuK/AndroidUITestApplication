package space.gavesha.devuplinkapplication

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = android.graphics.Color.BLACK,
            )
        )
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
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentTitleRes = when (currentRoute) {
        Destination.CONTACTS.route -> Destination.CONTACTS.labelRes
        Destination.MESSAGES.route -> Destination.MESSAGES.labelRes
        Destination.CALLING.route -> Destination.CALLING.labelRes
        Destination.NOTIFICATION.route -> Destination.NOTIFICATION.labelRes
        Destination.MORE.route -> Destination.MORE.labelRes
        else -> startDestination.labelRes
    }
    val currentTitle = stringResource(currentTitleRes)

    // @TODO
    // searchState should collected from the viewmodel
    var searchState by remember { mutableStateOf(TitleSearchBarUiState(currentTitle)) }

    LaunchedEffect(currentRoute) {
        searchState = searchState.copy(title = currentTitle)
        // @TODO
        // should update tile using viewmodel and searchState should collected from the viewmodel
    }

    Scaffold(
        modifier = modifier.windowInsetsPadding(
            WindowInsets.statusBars
                .union(WindowInsets.displayCutout)
                .only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)
        ),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TitleSearchBar(state = searchState, modifier = modifier)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = AppTheme.colors.primaryContainer,
                contentColor = AppTheme.colors.onPrimaryContainer,
                shape = RoundedCornerShape(percent = 50),
                onClick = {
                    Log.d("FloatingActionButton", "onClick add contact")
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        stringResource(R.string.cd_add_new_contact)
                    )
                },
                text = { Text(text = stringResource(R.string.contact)) },
            )
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
                    val isSelected = selectedDestination == index
                    val icon = if (isSelected) {
                        destination.selectedIcon
                    } else {
                        destination.defaultIcon
                    }
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
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

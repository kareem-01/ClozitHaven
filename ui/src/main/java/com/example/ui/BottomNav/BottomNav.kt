package com.example.ui.BottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ui.R
import com.example.ui.theme.CustomColors
import com.example.ui.utils.noRippleClick

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Composable
fun BottomNavigation(navController: NavHostController) {

    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Cart,
        BottomNavigationItem.Favorite,
        BottomNavigationItem.Profile,
    )
    val colors = MaterialTheme.CustomColors()

    Box() {
        NavigationBar(
            containerColor = colors.bottomNavColor,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(12.dp)
                )
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute(navController) == item.screenRoute,
                    icon = {
                        if (item is BottomNavigationItem.Cart) {
                            Box(
                                modifier = Modifier.offset(y = -50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.polygon_icon),
                                    contentDescription = null,
                                    tint = Color.Transparent
                                )

                            }
                        } else {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.screenRoute
                            )
                        }
                    },
                    onClick = {
                        navController.navigate(item.screenRoute) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colors.primary,
                        indicatorColor = colors.bottomNavColor,
                        unselectedIconColor = Color.White,
                    ),
                    modifier = Modifier
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-21).dp)
                .noRippleClick { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(59.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.polygon_icon),
                contentDescription = null,
                tint = MaterialTheme.CustomColors().primary
            )
            Icon(
                painter = painterResource(id = R.drawable.bag_icon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }


}

@Composable
fun CartCard() {

}
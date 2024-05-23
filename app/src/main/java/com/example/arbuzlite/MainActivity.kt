package com.example.arbuzlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.arbuzlite.data.database.DatabaseProvider
import com.example.arbuzlite.data.database.ProductDataInitializer
import com.example.arbuzlite.data.repository.ProductRepository
import com.example.arbuzlite.screens.basket.BasketScreen
import com.example.arbuzlite.screens.home.HomeScreen
import com.example.arbuzlite.ui.theme.ArbuzLiteTheme
import com.example.arbuzlite.viewmodel.ProductViewModel
import com.example.arbuzlite.viewmodel.ProductViewModelFactory


class MainActivity : ComponentActivity() {
    private val database by lazy { DatabaseProvider.getDatabase(this) }
    private val repository by lazy { ProductRepository(database.productDao()) }
    private val viewModel: ProductViewModel by viewModels { ProductViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductDataInitializer.initialize(database.productDao())
        enableEdgeToEdge()
        setContent {
            ArbuzLiteTheme {
                ArbuzLiteApp(viewModel)
            }
        }
    }
}

@Composable
fun ArbuzLiteApp(viewModel: ProductViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination
                val products by viewModel.allProducts.collectAsState(initial = emptyList())
                val totalItemsInBasket = products.sumOf { it.quantity }

                listOfNavItems.forEach { navItem: NavItem ->
                    val isBasket = navItem.route == Screens.Basket.name

                    NavigationBarItem(
                        selected = currentRoute?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (isBasket && totalItemsInBasket > 0) {
                                        Badge { Text(totalItemsInBasket.toString()) }
                                    }
                                }
                            )
                            {
                                Image(
                                    painter = painterResource(id = navItem.icon),
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            Text(navItem.label)
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController, startDestination = Screens.Home.name) {
                composable(Screens.Home.name) { HomeScreen(viewModel) }
                composable(Screens.Basket.name) { BasketScreen(viewModel) }
            }
        }
    }
}
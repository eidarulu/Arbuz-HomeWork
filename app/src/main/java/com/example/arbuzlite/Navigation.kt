package com.example.arbuzlite

data class NavItem(
    val label: String,
    val icon: Int,
    val route: String
)

enum class Screens {
    Home,
    Basket
}

val listOfNavItems: List<NavItem> = listOf(
    NavItem(label = "Home", icon = R.drawable.ic_home, route = Screens.Home.toString()),
    NavItem(label = "Basket", icon = R.drawable.ic_basket, route = Screens.Basket.toString())
)
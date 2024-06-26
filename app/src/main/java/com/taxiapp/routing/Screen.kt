package com.taxiapp.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object EnquiryScreen: Screen("enquiry_screen")
    object Detail: Screen("detail_screen")
    object Payment: Screen("payment_screen")


}
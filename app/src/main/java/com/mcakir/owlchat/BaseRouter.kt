package com.mcakir.owlchat

import androidx.navigation.NavController

open class BaseRouter {
    var navController: NavController = MainActivity.navController
        private set
}

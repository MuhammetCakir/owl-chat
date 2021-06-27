package com.mcakir.owlchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        navController = findNavController(this, R.id.nav_host_fragment)
    }

    companion object {
        lateinit var navController: NavController
            private set
    }
}
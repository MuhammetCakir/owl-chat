package com.mcakir.owlchat

import android.app.Application
import com.google.firebase.FirebaseApp

class OwlChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}

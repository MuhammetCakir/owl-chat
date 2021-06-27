package com.mcakir.owlchat.utils

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object FirebaseUtils {

    private const val DB_URL = "https://owl-chat-6a5ed-default-rtdb.europe-west1.firebasedatabase.app"
    private const val USERS_REF_PATH = "users"
    private const val MESSAGES_REF_PATH = "messages"

    fun getUsersReference(): DatabaseReference = getDB().getReference(USERS_REF_PATH)

    fun getMessagesReference(): DatabaseReference = getDB().getReference(MESSAGES_REF_PATH)

    private fun getDB(): FirebaseDatabase = Firebase.database(DB_URL)
}
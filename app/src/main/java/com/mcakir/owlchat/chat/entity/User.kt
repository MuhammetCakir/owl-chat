package com.mcakir.owlchat.chat.entity

import com.stfalcon.chatkit.commons.models.IUser

class User(val _name: String) : IUser {
    override fun getAvatar(): String? {
        return null
    }

    override fun getName(): String {
        return _name
    }

    override fun getId(): String {
        return _name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (_name != other._name) return false

        return true
    }

    override fun hashCode(): Int {
        return _name.hashCode()
    }
}
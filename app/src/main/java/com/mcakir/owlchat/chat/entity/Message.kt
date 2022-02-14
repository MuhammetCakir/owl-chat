package com.mcakir.owlchat.chat.entity

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

class Message(val _user: User, val _text: String, val _timestamp: String) :
    IMessage, Comparable<Message> {
    override fun getId(): String {
        return _timestamp
    }

    override fun getCreatedAt(): Date {
        return Date(_timestamp.toLong())
    }

    override fun getUser(): IUser {
        return _user
    }

    override fun getText(): String {
        return "${_user._name}: $_text"
    }

    override fun compareTo(other: Message): Int = this._timestamp.compareTo(other._timestamp)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (_user != other._user) return false
        if (_text != other._text) return false
        if (_timestamp != other._timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _user.hashCode()
        result = 31 * result + _text.hashCode()
        result = 31 * result + _timestamp.hashCode()
        return result
    }
}

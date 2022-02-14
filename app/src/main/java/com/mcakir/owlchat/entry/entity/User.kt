package com.mcakir.owlchat.entry.entity

data class User(val nickname: String?) {
    fun isModelValid(): Boolean = !nickname.isNullOrEmpty()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (nickname != other.nickname) return false

        return true
    }

    override fun hashCode(): Int {
        return nickname?.hashCode() ?: 0
    }
}

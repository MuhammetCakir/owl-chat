package com.mcakir.owlchat.entry

import com.mcakir.owlchat.entry.entity.User

interface EntryContracts {

    interface View {
        fun showStatusMessage(message: String)
        fun setNickname(nickname: String)
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()

        fun onJoinClicked(nickname: String?)
    }

    interface Interactor {
        fun addUser(user: User, completion: (error: String?) -> Unit)
    }

    interface Router {
        fun goToChatScreen(nickname: String)
    }
}
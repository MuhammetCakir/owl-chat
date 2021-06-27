package com.mcakir.owlchat.entry.presenter

import com.mcakir.owlchat.entry.EntryContracts
import com.mcakir.owlchat.entry.interactor.EntryInteractor
import com.mcakir.owlchat.entry.entity.User
import com.mcakir.owlchat.entry.router.EntryRouter

class EntryPresenter(val view: EntryContracts.View) : EntryContracts.Presenter {

    private var interactor: EntryContracts.Interactor? = null

    private lateinit var user: User

    override fun onCreate() {
        interactor = EntryInteractor(this)
    }

    override fun onDestroy() {
        interactor = null
    }

    override fun onJoinClicked(nickname: String?) {
        user = User(nickname)

        if (user.isModelValid())
            interactor?.addUser(user) {
                onJoinCompletion(it)
            }
        else
            view.showStatusMessage("Nickname is empty!")
    }

    private fun onJoinCompletion(error: String?) {
        error?.let {
            view.showStatusMessage(it)
        } ?: run {
            val router = EntryRouter()
            router.goToChatScreen(user.nickname!!)
        }
    }
}
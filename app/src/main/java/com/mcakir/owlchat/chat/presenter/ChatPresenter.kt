package com.mcakir.owlchat.chat.presenter

import android.os.Bundle
import com.mcakir.owlchat.chat.ChatContracts
import com.mcakir.owlchat.chat.entity.Message
import com.mcakir.owlchat.chat.entity.User
import com.mcakir.owlchat.chat.interactor.ChatInteractor
import com.stfalcon.chatkit.messages.MessagesListAdapter

class ChatPresenter(val view: ChatContracts.View) : ChatContracts.Presenter {

    private var interactor: ChatContracts.Interactor? = null

    private var adapter: MessagesListAdapter<Message>? = null
    private var nickname: String? = null
    private var messages: ArrayList<Message> = arrayListOf()

    override fun onCreate() {
        interactor = ChatInteractor(this)
    }

    override fun onViewCreated(bundle: Bundle?) {
        nickname = bundle?.getString("nickname") ?: "No Name"

        adapter = MessagesListAdapter(nickname, null)
        view.initMessageList(adapter!!)

        interactor?.observeMessages()
    }

    override fun messageSubmitted(message: String) {
        if (message.isEmpty()) {
            view.showToast("Message can not be empty!")
        } else {
            val messageObj: Message = Message(User(nickname!!), message, System.currentTimeMillis().toString())

            adapter?.addToStart(messageObj, true)
            messages.add(messageObj)
            interactor?.sendMessage(messageObj)
        }
    }

    override fun onMessagesChanged(messages: ArrayList<Message>) {
        adapter?.let {
            var difference: ArrayList<Message> = arrayListOf()

            messages.forEach { message ->
                if (this.messages.isEmpty())
                    difference = messages
                else {
                    if (!this.messages.contains(message)) difference.add(message)
                }
            }

            difference.sort()

            difference.forEach { message ->
                it.addToStart(message, true)
            }

            this.messages.addAll(difference.asIterable())
        }
    }

    override fun onServiceFail(error: String) {
        view.showToast(error)
    }
}
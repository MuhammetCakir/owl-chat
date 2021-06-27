package com.mcakir.owlchat.chat

import android.os.Bundle
import com.mcakir.owlchat.chat.entity.Message
import com.stfalcon.chatkit.messages.MessagesListAdapter

interface ChatContracts {

    interface View {
        fun initMessageList(adapter: MessagesListAdapter<Message>)

        fun showToast(message: String)
    }

    interface Presenter {
        fun onCreate()

        fun onViewCreated(bundle: Bundle?)

        fun messageSubmitted(message: String)

        fun onMessagesChanged(messages: ArrayList<Message>)

        fun onServiceFail(error: String)
    }

    interface Interactor {

        fun observeMessages()

        fun sendMessage(message: Message)
    }
}
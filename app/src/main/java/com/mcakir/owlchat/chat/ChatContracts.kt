package com.mcakir.owlchat.chat

import android.os.Bundle
import com.mcakir.owlchat.chat.entity.Message
import com.stfalcon.chatkit.messages.MessagesListAdapter

interface ChatContracts {

    interface View {
        /**
         * Initialize the message list adapter.
         *
         * @param adapter Adapter object will be set to message list adapter.
         *
         * @since 0.1.0
         */
        fun initMessageList(adapter: MessagesListAdapter<Message>)

        /**
         * Shows a toast message.
         *
         * @param message Message string will be displayed.
         *
         * @since 0.1.0
         */
        fun showToast(message: String)
    }

    interface Presenter {

        /**
         * View layer's onCreate cycle method.
         *
         * @since 0.1.0
         */
        fun onCreate()

        /**
         * View layer's onViewCreated cycle method.
         *
         * @since 0.1.0
         */
        fun onViewCreated(bundle: Bundle?)

        /**
         * This method will be called when the user submitted a message.
         *
         * @param message Message text will be submitted.
         *
         * @since 0.1.0
         */
        fun messageSubmitted(message: String)

        /**
         * This method will be called when the message list is updated.
         *
         * @param messages Updated message list.
         *
         * @since 0.1.0
         */
        fun onMessagesChanged(messages: ArrayList<Message>)

        /**
         * This method will be called when an error occurred in Firebase Realtime DB.
         *
         * @param error Error message.
         *
         * @since 0.1.0
         */
        fun onServiceFail(error: String)
    }

    interface Interactor {

        /**
         * Starts to observe message list changes in Firebase Realtime DB.
         *
         * @since 0.1.0
         */
        fun observeMessages()

        /**
         * Adds given message to the Firebase Realtime DB.
         *
         * @param message Message text.
         *
         * @since 0.1.0
         */
        fun sendMessage(message: Message)
    }
}

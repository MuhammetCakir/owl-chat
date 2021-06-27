package com.mcakir.owlchat.chat.interactor

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mcakir.owlchat.chat.ChatContracts
import com.mcakir.owlchat.chat.entity.Message
import com.mcakir.owlchat.chat.entity.User
import com.mcakir.owlchat.utils.FirebaseUtils

class ChatInteractor(val presenter: ChatContracts.Presenter) : ChatContracts.Interactor {

    override fun observeMessages() {
        FirebaseUtils.getMessagesReference().addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                presenter.onServiceFail(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val tempMessages: ArrayList<Message> = arrayListOf()

                snapshot.children.forEach { message ->
                    val timestamp: String? = message.child("timestamp").getValue<String>()
                    val author: String? = message.child("author").getValue<String>()
                    val text: String? = message.child("text").getValue<String>()

                    if (timestamp != null && author != null && text != null) {
                        tempMessages.add(Message(User(author), text, timestamp))
                    }
                }

                presenter.onMessagesChanged(tempMessages)
            }
        })
    }

    override fun sendMessage(message: Message) {
        val reference = FirebaseUtils.getMessagesReference()
        val newMessageObj = reference.push()

        newMessageObj.child("author").setValue(message._user._name)
        newMessageObj.child("text").setValue(message._text)
        newMessageObj.child("timestamp").setValue(message._timestamp)
    }
}
package com.mcakir.owlchat.entry.interactor

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mcakir.owlchat.entry.EntryContracts
import com.mcakir.owlchat.entry.entity.ServiceStatus
import com.mcakir.owlchat.entry.entity.User
import com.mcakir.owlchat.utils.FirebaseUtils

class EntryInteractor : EntryContracts.Interactor {

    var users: ArrayList<User> = arrayListOf()
    var serviceStatus: ServiceStatus = ServiceStatus.READY_FOR_USE

    constructor(presenter: EntryContracts.Presenter) {
        FirebaseUtils.getUsersReference().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                serviceStatus = ServiceStatus.READY_FOR_USE

                val tempUsers: ArrayList<User> = arrayListOf()

                dataSnapshot.children.forEach { child ->
                    child.getValue<String>()?.let {
                        val tempUser = User(it)
                        tempUsers.add(tempUser)
                    }
                }

                users = tempUsers
            }

            override fun onCancelled(error: DatabaseError) {
                serviceStatus = ServiceStatus.UNUSABLE
            }
        })
    }

    override fun addUser(user: User, completion: (error: String?) -> Unit) {

        if (users.contains(user)) {
            completion("Typed user already in use!")
        } else {
            FirebaseUtils.getUsersReference().push().setValue(user.nickname)
            completion(null)
        }
    }
}

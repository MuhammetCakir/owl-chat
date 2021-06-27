package com.mcakir.owlchat.entry.router

import androidx.core.os.bundleOf
import com.mcakir.owlchat.BaseRouter
import com.mcakir.owlchat.R
import com.mcakir.owlchat.entry.EntryContracts

class EntryRouter : BaseRouter(), EntryContracts.Router {

    override fun goToChatScreen(nickname: String) {
        val bundle = bundleOf("nickname" to nickname)
        navController.navigate(R.id.action_entryFragment_to_chatFragment, bundle)
    }
}
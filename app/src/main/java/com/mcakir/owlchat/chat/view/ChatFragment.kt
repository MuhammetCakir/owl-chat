package com.mcakir.owlchat.chat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mcakir.owlchat.R
import com.mcakir.owlchat.chat.ChatContracts
import com.mcakir.owlchat.chat.entity.Message
import com.mcakir.owlchat.chat.presenter.ChatPresenter
import com.mcakir.owlchat.databinding.FragmentChatBinding
import com.mcakir.owlchat.databinding.FragmentEntryBinding
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.messages.MessagesListAdapter

class ChatFragment : Fragment(), ChatContracts.View {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private var presenter: ChatContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ChatPresenter(this)
        presenter?.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.input.setInputListener {
            presenter?.messageSubmitted(it.toString())

            return@setInputListener true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.onViewCreated(arguments)
    }

    override fun initMessageList(adapter: MessagesListAdapter<Message>) {
        binding.messageList.setAdapter(adapter)
    }

    override fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }
}
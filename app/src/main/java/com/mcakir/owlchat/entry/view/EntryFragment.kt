package com.mcakir.owlchat.entry.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mcakir.owlchat.databinding.FragmentEntryBinding
import com.mcakir.owlchat.entry.EntryContracts
import com.mcakir.owlchat.entry.presenter.EntryPresenter


class EntryFragment : Fragment(), EntryContracts.View {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!

    private var presenter: EntryContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = EntryPresenter(this)
        presenter?.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEntryBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
            val nickname: String = binding.nicknameText.text.toString()
            presenter?.onJoinClicked(nickname)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.onDestroy()
        presenter = null
    }

    override fun showStatusMessage(message: String) {
        binding.statusText.text = message
    }
}
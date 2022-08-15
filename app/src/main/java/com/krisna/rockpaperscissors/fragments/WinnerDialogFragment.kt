package com.krisna.rockpaperscissors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.krisna.rockpaperscissors.CallBackResultInteraction
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.databinding.FragmentDialogBinding

class WinnerDialogFragment() : DialogFragment(R.layout.fragment_dialog) {

    lateinit var name: String
    private lateinit var callback: CallBackResultInteraction
    private var winnerFragmentBinding: FragmentDialogBinding? = null
    private val binding get() = winnerFragmentBinding!!


    constructor(name: String,callback: CallBackResultInteraction):this(){
        this.name = name
        this.callback = callback
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        winnerFragmentBinding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textWinner.text = name

        if (name == "SERI") {
            binding.textMenang.text = ""
        }

        binding.btnKembalikemenu.setOnClickListener {
            callback.finishGame()
            dismiss()
        }

        binding.btnmainLagi.setOnClickListener {
            callback.resetGame()
            dismiss()
        }
    }

}
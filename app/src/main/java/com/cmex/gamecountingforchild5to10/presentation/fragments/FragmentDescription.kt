package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.databinding.FragmentDescriptionBinding
import com.cmex.gamecountingforchild5to10.presentation.myLog


class FragmentDescription : Fragment() {
     private lateinit var  binding:FragmentDescriptionBinding
 lateinit var listener:ListenerCloseDescription
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ListenerCloseDescription) listener=context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding=FragmentDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickOk()
    }
    private fun onClickOk(){
        binding.ibtnOk.setOnClickListener {

            activity?.supportFragmentManager?.popBackStack()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener.onCloseDescription()
    }
 interface ListenerCloseDescription{
    fun onCloseDescription()
 }

    companion object {

        fun newInstance() =
            FragmentDescription().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
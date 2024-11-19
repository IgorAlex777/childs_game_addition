package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.databinding.FragmentLevelBinding
import com.cmex.gamecountingforchild5to10.presentation.myLog


class FragmentLevel : Fragment() {
    private lateinit var binding:FragmentLevelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=FragmentLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myLog("FragmentLevel onViewCreated")
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }

    companion object {

        fun newInstance() =
            FragmentLevel().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
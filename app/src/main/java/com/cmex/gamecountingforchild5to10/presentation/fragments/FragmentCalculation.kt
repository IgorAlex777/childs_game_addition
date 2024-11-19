package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.gamecountingforchild5to10.R


class FragmentCalculation : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculation, container, false)
    }

    companion object {

        fun newInstance() =
            FragmentCalculation().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
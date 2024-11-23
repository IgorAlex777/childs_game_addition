package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.data.SharedPrefGame
import com.cmex.gamecountingforchild5to10.databinding.FragmentDescriptionBinding


class FragmentDescription : Fragment() {
     private lateinit var  binding:FragmentDescriptionBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding=FragmentDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name="imageView"
        onClickStart()
        onChecking()
    }
    private fun onClickStart(){
        binding.ibStart.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()


        }
    }
    private fun onChecking(){
        binding.checkBoxDescription.setOnCheckedChangeListener { _, cheking ->
            if(cheking){
               SharedPrefGame.setScreenDescription(true)
            } else {
                SharedPrefGame.setScreenDescription(false)

            }
        }
    }
    private fun getFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.conteiner,fragment)
            .addToBackStack(FragmentLevel.NAME_LEVEL_FRAGMENT)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
     getFragment(FragmentLevel.newInstance())
    }


    companion object {

        fun newInstance() =
            FragmentDescription().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
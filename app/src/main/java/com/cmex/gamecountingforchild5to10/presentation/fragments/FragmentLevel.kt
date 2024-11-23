package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.databinding.FragmentLevelBinding
import com.cmex.gamecountingforchild5to10.domain.entity.Level
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

        onListenerClickLevel()
    }
    private fun onListenerClickLevel(){
        val listener=selectLevel()
        binding.tvTest.setOnClickListener(listener)
        binding.tvLevelEasy.setOnClickListener(listener)
        binding.tvLevelMiddle.setOnClickListener(listener)
        binding.tvLevelHard.setOnClickListener(listener)

    }
      private fun selectLevel():OnClickListener{
          return OnClickListener {
              when(it){
                  binding.tvTest->{getFragment(FragmentCalculation.newInstance(Level.TEST))}
                  binding.tvLevelEasy->{getFragment(FragmentCalculation.newInstance(Level.EASY))}
                  binding.tvLevelMiddle->{getFragment(FragmentCalculation.newInstance(Level.MIDDLE))}
                  binding.tvLevelHard->{getFragment(FragmentCalculation.newInstance(Level.HARD))}
              }
          }
      }
    private fun getFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.conteiner,fragment)
            .addToBackStack(FragmentCalculation.NAME_CALCULATION)
            .commit()
    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }

    companion object {
      const val NAME_LEVEL_FRAGMENT="level_mode"
        fun newInstance() =
            FragmentLevel().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
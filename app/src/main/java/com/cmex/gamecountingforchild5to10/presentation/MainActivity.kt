package com.cmex.gamecountingforchild5to10.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.data.GameImpl
import com.cmex.gamecountingforchild5to10.presentation.fragments.FragmentDescription
import com.cmex.gamecountingforchild5to10.presentation.fragments.FragmentLevel
import java.lang.Integer.max
import java.lang.Integer.min

class MainActivity : AppCompatActivity(),FragmentDescription.ListenerCloseDescription {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        utilSetColorStatusBar(this,ContextCompat.getColor(this,R.color.white_middle_font))

        loadFragmentDescription()
           myLog("onCreate")
    }
    private fun loadFragmentDescription(){
      getFragment(FragmentDescription.newInstance())
    }
    private fun getFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.conteiner,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCloseDescription() {
        myLog("onCloseDescription()")
       getFragment(FragmentLevel.newInstance())
    }
}
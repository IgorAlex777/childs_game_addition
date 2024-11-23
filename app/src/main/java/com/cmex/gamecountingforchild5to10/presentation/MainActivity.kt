package com.cmex.gamecountingforchild5to10.presentation

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.data.SharedPrefGame
import com.cmex.gamecountingforchild5to10.presentation.fragments.FragmentDescription
import com.cmex.gamecountingforchild5to10.presentation.fragments.FragmentLevel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        utilSetColorStatusBar(this,ContextCompat.getColor(this,R.color.white_middle_font))
       // adjustFontScale(resources.configuration)

           if(SharedPrefGame.getScreenDescription()){
               getFragment(FragmentLevel.newInstance())
           }else {
               loadFragmentDescription()
           }

    }
    private fun adjustFontScale(configuration: Configuration?) {
        configuration?.let {
            it.fontScale = 1.0F
            val metrics: DisplayMetrics = resources.displayMetrics
            val wm: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            @Suppress("DEPRECATION")
            wm.defaultDisplay.getMetrics(metrics)
            @Suppress("DEPRECATION")
            metrics.scaledDensity = it.fontScale * metrics.density

            baseContext.applicationContext.createConfigurationContext(it)
            baseContext.resources.displayMetrics.setTo(metrics)

        }
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


}
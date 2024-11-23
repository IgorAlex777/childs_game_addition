package com.cmex.gamecountingforchild5to10.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.cmex.gamecountingforchild5to10.presentation.MyApp

object  SharedPrefGame {
    private const val NAME_BOX_BASE="base_check_box"
    private const val CHECK_BOX="check_box"

    private val context=MyApp.instance
 private   val baseSharedPref= context.getSharedPreferences(NAME_BOX_BASE,Context.MODE_PRIVATE)

   fun getScreenDescription():Boolean{
       return baseSharedPref.getBoolean(CHECK_BOX,false)
   }
    fun setScreenDescription(flag: Boolean){
        val editor= baseSharedPref.edit()
        editor.putBoolean(CHECK_BOX,flag)
        editor.apply()
    }
}
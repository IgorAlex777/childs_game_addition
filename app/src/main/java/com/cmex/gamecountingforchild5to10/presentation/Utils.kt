package com.cmex.gamecountingforchild5to10.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowInsetsControllerCompat
import java.text.SimpleDateFormat
import java.util.Date

fun myLog(text:String){
     Log.d("CMEX",text).toString()
}

@SuppressLint("SimpleDateFormat")
fun utilDate(time:Long):String{
    val date=Date(time)
    return SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date)
}

@SuppressLint("SimpleDateFormat")
fun utilTextDateToDate(dateText:String,pattern:String):Long{
    if(pattern.length==dateText.length){
    val date=SimpleDateFormat(pattern).parse(dateText)
    date?.let {
        return date.time
    }
    }
   return 0
}
//устанвливаем цвет статус бара. И взависимости от цвета фона устанавливем цвет текста
fun utilSetColorStatusBar(activity: Activity, color:Int){
    val window= activity.window
    window.statusBarColor= color
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
        !isDark(color)
}

private fun isDark(color: Int): Boolean {
    return ColorUtils.calculateLuminance(color) < 0.5
}
//=====================================================================================
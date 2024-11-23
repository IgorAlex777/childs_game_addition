package com.cmex.gamecountingforchild5to10.domain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
     val maxSum:Int,
    val minCountRightAnswer:Int,
    val minPercentRightAnswer:Int,
    val gameTimeSec:Int
) :Parcelable
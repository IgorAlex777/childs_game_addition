package com.cmex.gamecountingforchild5to10.domain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
     val winner:Boolean,
     val countRightAnswers:Int,
     val countQuestions:Int,
     val gameSettings: GameSettings
) :Parcelable
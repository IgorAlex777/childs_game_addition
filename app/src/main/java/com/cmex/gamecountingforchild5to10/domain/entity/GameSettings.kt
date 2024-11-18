package com.cmex.gamecountingforchild5to10.domain.entity

data class GameSettings(
    private val maxSum:Int,
    private val minCountRightAnswer:Int,
    private val minPercentRightAnswer:Int,
    private val gameTimeSec:Int
)

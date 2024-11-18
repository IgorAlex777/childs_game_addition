package com.cmex.gamecountingforchild5to10.domain.entity

data class GameResult(
    private val winner:Boolean,
    private val countRightAnswers:Int,
    private val countQuestions:Int,
    private val gameSettings: GameSettings
)

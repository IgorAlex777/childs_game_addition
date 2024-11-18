package com.cmex.gamecountingforchild5to10.domain.repository

import com.cmex.gamecountingforchild5to10.domain.entity.GameSettings
import com.cmex.gamecountingforchild5to10.domain.entity.Level
import com.cmex.gamecountingforchild5to10.domain.entity.Question

interface RepositoryGame {
    fun getSettingsGame(level: Level):GameSettings
    fun generationQuestion(maxSum:Int,countAnswers:Int):Question
}
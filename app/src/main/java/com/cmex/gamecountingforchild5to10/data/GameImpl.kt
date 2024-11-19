package com.cmex.gamecountingforchild5to10.data

import com.cmex.gamecountingforchild5to10.domain.entity.GameSettings
import com.cmex.gamecountingforchild5to10.domain.entity.Level
import com.cmex.gamecountingforchild5to10.domain.entity.Question
import com.cmex.gamecountingforchild5to10.domain.repository.RepositoryGame
import com.cmex.gamecountingforchild5to10.presentation.myLog
import java.lang.Integer.max
import java.lang.Math.min

object GameImpl:RepositoryGame {

    private const val MIN_ANSWER=2
    private const val MIN_VISIBLE=1

    override fun getSettingsGame(level: Level): GameSettings {
        return when(level){
            Level.TEST->{
                GameSettings(10,3,60,10)
            }

            Level.EASY->{
                GameSettings(10,15,80,60)
            }

            Level.MIDDLE->{
                GameSettings(20,20,80,45)
            }

            Level.HARD->{
                GameSettings(30,25,80,40)
            }
        }

    }


    override fun generationQuestion(maxSum: Int, countAnswers: Int): Question {
       val sum=(MIN_ANSWER..maxSum).random()
        val visibleNumber=(MIN_VISIBLE until sum).random()
        val answer=sum-visibleNumber
        val listAnswers= hashSetOf<Int>(countAnswers)
        listAnswers.add(answer)

        while(listAnswers.size< countAnswers){

            listAnswers.add((MIN_VISIBLE..<maxSum).random())
        }
        return Question(sum,visibleNumber,listAnswers.toList())
    }
}
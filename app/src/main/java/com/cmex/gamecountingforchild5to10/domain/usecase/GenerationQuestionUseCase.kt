package com.cmex.gamecountingforchild5to10.domain.usecase

import com.cmex.gamecountingforchild5to10.domain.entity.Question
import com.cmex.gamecountingforchild5to10.domain.repository.RepositoryGame

class GenerationQuestionUseCase(private val repositoryGame: RepositoryGame) {
    operator fun invoke(maxSum:Int):Question{
        return repositoryGame.generationQuestion(maxSum, COUNT)
    }
    companion object{
        private const val COUNT=6
    }
}
package com.cmex.gamecountingforchild5to10.domain.usecase

import com.cmex.gamecountingforchild5to10.domain.entity.GameSettings
import com.cmex.gamecountingforchild5to10.domain.entity.Level
import com.cmex.gamecountingforchild5to10.domain.repository.RepositoryGame

class GameSettingsUseCase( private val repositoryGame:RepositoryGame) {

    operator fun invoke( level: Level):GameSettings{
       return repositoryGame.getSettingsGame(level)
    }

}
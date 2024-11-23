package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmex.gamecountingforchild5to10.domain.entity.Level
import com.cmex.gamecountingforchild5to10.presentation.ViewModelGame
import com.google.android.material.R


class ViewModelFactoryGame(private val level: Level):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewModelGame::class.java)) {
            return ViewModelGame(level) as T
        }
        throw RuntimeException("Это не ViewModelGame а это-$modelClass")
    }
}
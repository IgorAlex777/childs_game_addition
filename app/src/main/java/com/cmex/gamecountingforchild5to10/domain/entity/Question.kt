package com.cmex.gamecountingforchild5to10.domain.entity

data class Question (
    private val sumNumbers:Int,
    private val visibleNumber:Int,
    private val listAnswers:List<Int>
)
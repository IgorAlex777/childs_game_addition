package com.cmex.gamecountingforchild5to10.domain.entity

data class Question (
     val sumNumbers:Int,
     val visibleNumber:Int,
     val listAnswers:List<Int>
){
     val rightAnswer=sumNumbers-visibleNumber
}
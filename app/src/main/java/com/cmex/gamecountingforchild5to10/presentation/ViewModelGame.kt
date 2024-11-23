package com.cmex.gamecountingforchild5to10.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.data.GameImpl
import com.cmex.gamecountingforchild5to10.domain.entity.GameResult
import com.cmex.gamecountingforchild5to10.domain.entity.GameSettings
import com.cmex.gamecountingforchild5to10.domain.entity.Level
import com.cmex.gamecountingforchild5to10.domain.entity.Question
import com.cmex.gamecountingforchild5to10.domain.usecase.GameSettingsUseCase
import com.cmex.gamecountingforchild5to10.domain.usecase.GenerationQuestionUseCase

class ViewModelGame(private val level: Level):ViewModel() {
    private lateinit var gameSettings: GameSettings
    private lateinit var timer: CountDownTimer
    private val repository=GameImpl
    private val gameSettingsUseCase=GameSettingsUseCase(repository)
    private val generationQuestionUseCase=GenerationQuestionUseCase(repository)
    private var countOfRightAnswers=0
    private var countOfAllAnswers=0
     private val context=MyApp.instance

    //результат игры
    private val _gameResultModel=MutableLiveData<GameResult>()
    val gameResultModel:LiveData<GameResult>
        get() = _gameResultModel

   // вопрос игры
  private  val _questionModel=MutableLiveData<Question>()
    val questionModel: LiveData<Question>
         get() = _questionModel

    //  настройки игры
   private val _settingsModel=MutableLiveData<GameSettings>()
    val settingsModel:LiveData<GameSettings>
        get() = _settingsModel
    //правильный ответ
   private val _rightAnswer=MutableLiveData<Int>()
    val rightAnswer:LiveData<Int>
        get()=_rightAnswer
    // процент правильных ответов
    private val _percentRightAnswer=MutableLiveData<Int>()
    val percentRightAnswer:LiveData<Int>
        get()=_percentRightAnswer
    // установка текста с количеством правильных ответов
    private val _settingInfoText=MutableLiveData<String>()
    val settingInfoText:LiveData<String>
        get() = _settingInfoText
    // количество правильных ответов
    private val _countingRightAnswer=MutableLiveData<Int>()
    val countingRightAnswer:LiveData<Int>
        get()=_countingRightAnswer
    //таймер обратного отсчета
    private val _timerModel=MutableLiveData<String>()
    val timerModel:LiveData<String>
        get() = _timerModel

    // финиш таймера
    private val _stopTimer=MutableLiveData<Boolean>()
    val stopTimer:LiveData<Boolean>
        get() = _stopTimer
init {
    myLog("viewModel start")
    onGameStart()
}
  private  fun onGameStart(){
        getSettings()
        onStartDownTimer()
        getQuestion()

    }
    private fun onStartDownTimer(){
        timer=object :CountDownTimer(gameSettings.gameTimeSec*1000L,1000){
            override fun onTick(msec: Long) {
                _timerModel.value=formatMSecToString(msec)
            }
            override fun onFinish() {
               myLog("end countDown timer")
                onStopTimer()
            }
        }.start() //***** надо помнить про start******************
    }
    private fun formatMSecToString(mSec:Long): String {
        val secondsTemp=(mSec/1000)
        val minutes=secondsTemp/60
        val seconds=secondsTemp-(minutes*60)
       return String.format("%02d:%02d",minutes,seconds)
    }
   private fun getQuestion(){

       _questionModel.value=generationQuestionUseCase(gameSettings.maxSum)
    }
  private  fun getSettings(){
        gameSettings=gameSettingsUseCase(level)
        _settingsModel.value=gameSettings
    }
    //запуск формирования нового вопроса
    fun retryQuestion(answer:Int){
        checkAnswer(answer)
        getQuestion()
    }
    private fun checkAnswer(answer:Int){
        if(answer==questionModel.value?.rightAnswer){
            countOfRightAnswers++
        }
        countOfAllAnswers++
         if(countOfRightAnswers!=0){
             onPercentRightAnswers()
         }
        _settingInfoText.value=setTextInTextView()
    }
    private fun onPercentRightAnswers(){
         val percentTemp=(countOfAllAnswers/gameSettings.minCountRightAnswer.toFloat())
        val percent=(percentTemp*100).toInt()
        _percentRightAnswer.value=percent
    }
    private fun setTextInTextView(): String {
        return context.resources.getString(
            R.string.count_right_answers,
            countOfRightAnswers.toString(),
            gameSettings.minCountRightAnswer.toString()
        )
    }
    private fun onResult():GameResult{

        var percentRight=0
            _percentRightAnswer.value?.let {
            percentRight=it
        }
        val winner=(countOfRightAnswers>=gameSettings.minCountRightAnswer)&&
                (percentRight >=gameSettings.minPercentRightAnswer)
        myLog("Winner=$winner")
       return GameResult(winner,countOfRightAnswers,countOfAllAnswers,gameSettings)
    }
    private fun onStopTimer(){
        _gameResultModel.value=onResult()
        _stopTimer.value=true

    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}
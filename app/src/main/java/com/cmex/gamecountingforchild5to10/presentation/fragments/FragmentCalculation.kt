package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.databinding.FragmentCalculationBinding
import com.cmex.gamecountingforchild5to10.domain.entity.GameResult
import com.cmex.gamecountingforchild5to10.domain.entity.GameSettings
import com.cmex.gamecountingforchild5to10.domain.entity.Level
import com.cmex.gamecountingforchild5to10.domain.entity.Question
import com.cmex.gamecountingforchild5to10.presentation.ViewModelGame
import com.cmex.gamecountingforchild5to10.presentation.myLog


class FragmentCalculation : Fragment() {
    private lateinit var level: Level
    private lateinit var binding:FragmentCalculationBinding
    private val modelFactory by lazy { ViewModelFactoryGame(level) }
    private  val model by lazy { ViewModelProvider(this,modelFactory) [ViewModelGame::class.java] }
    private lateinit var settings: GameSettings
    private lateinit var question: Question
    private lateinit var gameResult: GameResult
    private val listViewAnswers by lazy {
        mutableListOf<TextView>().apply {
            with(binding){
                add(tv1)
                add(tv2)
                add(tv3)
                add(tv4)
                add(tv5)
                add(tv6)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCheckBuildAndParcelable()
    }
    private fun onCheckBuildAndParcelable(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.TIRAMISU){
            @Suppress("DEPRECATION")
            arguments?.getParcelable<Level>(MODE_LEVEL)?.let {
                level=it
            }
        }else{
            arguments?.getParcelable(MODE_LEVEL,Level::class.java)?.let {
                level=it
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     binding=FragmentCalculationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onListenerClickAnswer()
        initObservers()

    }


    private fun initObservers(){
        model.settingsModel.observe(viewLifecycleOwner){
            settings=it
            onSettingBegin()
        }
        model.questionModel.observe(viewLifecycleOwner){
            question=it
            onSetScreenQuestion()
        }
        model.timerModel.observe(viewLifecycleOwner){
            binding.tvTimer.text=it.toString()
        }
        model.stopTimer.observe(viewLifecycleOwner){
            if(it){

                getFragment(FragmentResult.newInstance(gameResult))
            }
        }
        model.percentRightAnswer.observe(viewLifecycleOwner){

         val  color = if(it>settings.minPercentRightAnswer){
           requireActivity().getColor(R.color.lime)
            }else {
            requireActivity().getColor(R.color.red)
            }

            binding.progressBar.progressTintList= ColorStateList.valueOf(color)
            binding.progressBar.progress=it

        }
        model.settingInfoText.observe(viewLifecycleOwner){
            binding.tvCountRightAnswers.text=it
        }
        model.gameResultModel.observe(viewLifecycleOwner){
            gameResult=it
        }
    }
    private fun onListenerClickAnswer()=with(binding){
        for(itemView in listViewAnswers){
            itemView.setOnClickListener{
                model.retryQuestion(itemView.text.toString().toInt())
            }
        }
    }

    private fun onSetScreenQuestion()=with(binding){
        tvAnswer.text=question.sumNumbers.toString()
        tvVisibleNumber.text=question.visibleNumber.toString()

        for(i in 0 until question.listAnswers.size){
            listViewAnswers[i].text=question.listAnswers[i].toString()
        }

    }
     private fun onSettingBegin()=with(binding){
         progressBar.max=100//settings.minCountRightAnswer
         progressBar.secondaryProgress=settings.minPercentRightAnswer
         tvTimer.text=String.format("00:%02d",settings.gameTimeSec)
         tvCountRightAnswers.text=resources
             .getString(R.string.count_right_answers,
                 "0",
                 settings.minCountRightAnswer.toString())
     }
    private fun getFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.conteiner,fragment)
            .addToBackStack(null)
            .commit()
    }
    companion object {
        private const val MODE_LEVEL="modeLevel"
        const val NAME_CALCULATION="level_calculation"
        fun newInstance(level: Level) =
            FragmentCalculation().apply {
                arguments = Bundle().apply {
                 putParcelable(MODE_LEVEL,level)
                }
            }
    }
}
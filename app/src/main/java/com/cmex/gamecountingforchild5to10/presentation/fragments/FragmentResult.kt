package com.cmex.gamecountingforchild5to10.presentation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.view.PointerIconCompat.load
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.cmex.gamecountingforchild5to10.R
import com.cmex.gamecountingforchild5to10.databinding.FragmentResultBinding
import com.cmex.gamecountingforchild5to10.domain.entity.GameResult
import com.cmex.gamecountingforchild5to10.presentation.myLog


class FragmentResult : Fragment() {
  private lateinit var binding:FragmentResultBinding
   private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      parseArgumentsFromBuildSdk()
    }
     private fun parseArgumentsFromBuildSdk(){
         if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU) {
             arguments?. getParcelable(RESULT, GameResult::class.java)?.let {
                 gameResult=it
             }
         }else {
             @Suppress("DEPRECATION")
             arguments?.getParcelable<GameResult>(RESULT)?.let{
                 gameResult=it
             }
         }
     }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myLog("FragmentResult onViewCreated")
        onClickContinue()
        onClickStop()
        onClickBack()
        onSetScreenResult()
    }
    private fun onSetScreenResult()=with(binding){
        if(gameResult.winner){
            tvTitleResult.text=getString(R.string.text_victory)
            Glide.with(this@FragmentResult)
                .load(R.drawable.correct_answer)
                .error(R.drawable.no)
                .into(binding.ivResult)
        }else{
            tvTitleResult.text=getString(R.string.text_no_luck)
            Glide.with(this@FragmentResult)
                .load(R.drawable.loss_gif)
                .error(R.drawable.no)
                .into(binding.ivResult)
        }
        tvCountAnswer.text=getString(R.string.right_answers,
            gameResult.countRightAnswers,
            gameResult.gameSettings.minCountRightAnswer)
        val percent=((gameResult.countRightAnswers/gameResult.countQuestions.toDouble())*100).toInt()
        tvPercentAnswer.text=getString(R.string.percent_answers,
            percent,
            gameResult.gameSettings.minPercentRightAnswer)
    }
      private fun onClickContinue(){
          binding.tvContinue.setOnClickListener {
            goToFragmentLevel()
          }
      }
    private fun goToFragmentLevel(){
        requireActivity().supportFragmentManager.popBackStack(FragmentCalculation.NAME_CALCULATION,1)

    }
    private fun onClickStop(){
        binding.tvStopGame.setOnClickListener {
            activity?.finish()
        }

    }
    private fun onClickBack(){
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner,object :OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                  goToFragmentLevel()
                }

            })
    }
    companion object {
        private const val RESULT="gameResult"

        fun newInstance(gameResult: GameResult) =
            FragmentResult().apply {
                arguments = Bundle().apply {
                  putParcelable(RESULT,gameResult)
                }
            }
    }
}
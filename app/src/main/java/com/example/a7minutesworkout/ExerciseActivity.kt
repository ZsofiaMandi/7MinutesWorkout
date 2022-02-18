package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding : ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null // Timer for resting, these are the breaks
    private var restProgress: Int = 0
    private var restDuration: Int = 10

    private var exerciseTimer: CountDownTimer? = null // Timer for exercising
    private var exerciseProgress: Int = 0
    private var exerciseDuration: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Allowing the Action bar
        setSupportActionBar(binding?.toolBarExercise)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolBarExercise?.setNavigationOnClickListener{
            onBackPressed() // Doing a normal go back action
        }

        setupRestView()

    }

    private fun setupRestView(){
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvTitle?.text = "GET READY FOR"
        binding?.flProgressBarExercise?.visibility = View.INVISIBLE
        binding?.flProgressBarRest?.visibility = View.VISIBLE
        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBarRest?.max = restDuration
        binding?.progressBarRest?.progress = restDuration - restProgress
        restTimer = object: CountDownTimer(restDuration.toLong() * 1000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBarRest?.progress = restDuration - restProgress
                binding?.tvTimerRest?.text = (restDuration - restProgress).toString()
            }

            override fun onFinish() {
                setupExerciseView("Exercise name")
            }

        }.start()
    }

    private fun setupExerciseView(exerciseName: String){
        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding?.tvTitle?.text = exerciseName
        binding?.flProgressBarRest?.visibility = View.INVISIBLE
        binding?.flProgressBarExercise?.visibility = View.VISIBLE
        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.max = exerciseDuration
        binding?.progressBarExercise?.progress = exerciseDuration - exerciseProgress
        exerciseTimer = object: CountDownTimer(exerciseDuration.toLong() * 1000, 1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = exerciseDuration - exerciseProgress
                binding?.tvTimerExercise?.text = (exerciseDuration - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                    "Here will start the rest", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding = null
    }
}
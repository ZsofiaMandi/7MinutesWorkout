package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding : ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null // Timer for resting, these are the breaks
    private var restProgress: Int = 0
    private var restDuration: Int = 10

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

        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restDuration - restProgress
        restTimer = object: CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = restDuration - restProgress
                binding?.tvTimer?.text = (restDuration - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                "Here we will start the exercise", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        binding = null
    }
}
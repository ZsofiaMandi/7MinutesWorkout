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
    private var restDuration: Int = 3

    private var exerciseTimer: CountDownTimer? = null // Timer for exercising
    private var exerciseProgress: Int = 0
    private var exerciseDuration: Int = 5

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Allowing the Action bar
        setSupportActionBar(binding?.toolBarExercise)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // Creating the exercise list from the constants
        exerciseList = Constants.defaultExerciseList()

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

        // Making the exercise view invisible and the rest view visible
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.flProgressBarExercise?.visibility = View.INVISIBLE
        binding?.flProgressBarRest?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingExercise?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()

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
                currentExercisePosition++
                setupExerciseView()
            }

        }.start()
    }

    private fun setupExerciseView(){
        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        // Making the rest view invisible and the exercise view visible
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.flProgressBarRest?.visibility = View.INVISIBLE
        binding?.tvUpcomingExercise?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.flProgressBarExercise?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE

        // Setting the current exercise values
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()


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
                if(currentExercisePosition < exerciseList?.size!! - 1){
                    setupRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity,
                    "Congratulations! You have completed the 7 minutes workout",
                    Toast.LENGTH_SHORT).show()
                }
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
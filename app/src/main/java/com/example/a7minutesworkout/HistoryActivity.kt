package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Allowing the Action bar
        setSupportActionBar(binding?.toolBarHistory)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }

        binding?.toolBarHistory?.setNavigationOnClickListener{
            onBackPressed()
        }

        val historyDao = (application as WorkoutApp).db.historyDao()

        lifecycleScope.launch {
            historyDao.fetchAllWorkouts().collect{
                val workoutList = ArrayList(it)
                Log.e("Fetched dates: ", "" +it)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        
        binding = null
    }
}
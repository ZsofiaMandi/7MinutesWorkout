package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarFinishActivity)

        binding?.toolBarFinishActivity?.setNavigationOnClickListener{
            onBackPressed() // Doing a normal go back action
        }

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.btnFinish?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val historyDao = (application as WorkoutApp).db.historyDao()
        addDataToDatabase(historyDao)
    }

    private fun addDataToDatabase(historyDao: HistoryDao){

        val mCalendar = Calendar.getInstance()
        val dateTime = mCalendar.time
        Log.e("Date: ", "" +dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Date: ", "" +date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e("Date: ", "Added...")
        }
    }
}
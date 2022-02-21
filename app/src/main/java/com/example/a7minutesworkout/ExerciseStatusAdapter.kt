package com.example.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val exerciseList: ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemExerciseStatusBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // We want to return our ViewHolder
        return ViewHolder(ItemExerciseStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise: ExerciseModel = exerciseList[position]
        holder.tvItem.text = exercise.getId().toString()
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}
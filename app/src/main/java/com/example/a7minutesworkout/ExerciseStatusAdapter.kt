package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        // What we want to do for every single binding
        val exercise: ExerciseModel = exerciseList[position]
        holder.tvItem.text = exercise.getId().toString()

        when{
            exercise.getIsSelected() ->{
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_thin_color_accent_border)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            exercise.getIsCompleted() ->{
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else ->{
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_gray_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}
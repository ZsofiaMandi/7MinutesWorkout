package com.example.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter (private val items: ArrayList<HistoryEntity>)
    : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        val llMain = binding.llMain
        val tvPosition = binding.tvPosition
        val tvItem = binding.tvItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = item.date

        if(position % 2 == 0){
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(context,
                R.color.lightGray))
        }else{
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(context,
                R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}
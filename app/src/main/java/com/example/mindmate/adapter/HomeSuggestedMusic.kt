package com.example.mindmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindmate.R

class HomeSuggestedMusic(private val musicData : ArrayList<Musicdata>)
    : RecyclerView.Adapter<HomeSuggestedMusic.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.suggested_music_home, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return musicData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = musicData[position]
        holder.videoId = currentItem.videoId
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        lateinit var videoId : String
    }
}
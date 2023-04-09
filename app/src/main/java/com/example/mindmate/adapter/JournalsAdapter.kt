package com.example.mindmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindmate.R
import com.example.mindmate.entities.Journals

class JournalsAdapter(val arrayList: List<Journals>) :
    RecyclerView.Adapter<JournalsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_journal_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = arrayList[position].title
        holder.tvDesc.text = arrayList[position].journalText
        holder.tvDateTime.text = arrayList[position].dateTime
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val tvDesc : TextView = itemView.findViewById(R.id.tvDesc)
        val tvDateTime : TextView = itemView.findViewById(R.id.tvDateTime)
    }

}
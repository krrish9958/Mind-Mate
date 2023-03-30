package com.example.mindmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindmate.R

class HomeDocs(private val docsList : ArrayList<DocsData>) : RecyclerView.Adapter<HomeDocs.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.suggested_doc_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return docsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = docsList[position]
        holder.image.setImageResource(currentItem.image)
        holder.name.text = currentItem.name
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.doc_image)
        val name : TextView = itemView.findViewById(R.id.doc_name)
    }

}
package com.example.tringconnectapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tringconnectapp.R
import com.example.tringconnectapp.data.Details
import com.example.tringconnectapp.data.PeopleList

class DetailsAdapter(private val dList: List<Details>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.people_posts, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = dList[position]
        //holder.profile.setImageResource(itemsViewModel.image)
        holder.people.text = itemsViewModel.name
       // holder.designation.text = itemsViewModel.content
        //holder.description.text = itemsViewModel.desc
        //holder.hashtag.text = itemsViewModel.hash
        //holder.timeline.text = itemsViewModel.time
    }

    override fun getItemCount(): Int {
        return dList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profile: ImageView = itemView.findViewById(R.id.circle_profile)
        val people: TextView = itemView.findViewById(R.id.people_name)
        val designation: TextView = itemView.findViewById(R.id.people_designation)
        val description: TextView = itemView.findViewById(R.id.content)
        val hashtag: TextView = itemView.findViewById(R.id.hastag)
        val timeline: TextView = itemView.findViewById(R.id.time_text)

    }
}
package com.example.tringconnectapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tringconnectapp.R
import com.example.tringconnectapp.data.Courses

class CoursesAdapter(private val fList: List<Courses>) :
    RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lastest_courses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = fList[position]
        holder.timespace.text = itemsViewModel.timeline
    }

    override fun getItemCount(): Int {
        return fList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val timespace: TextView = itemView.findViewById(R.id.time_text)

    }
}
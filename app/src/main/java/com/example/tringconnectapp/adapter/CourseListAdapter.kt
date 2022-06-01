package com.example.tringconnectapp.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tringconnectapp.R
import com.example.tringconnectapp.data.CourseList

class CourseListAdapter(private val cList: List<CourseList>) :
    RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = cList[position]
        holder.courseimage.setImageResource(itemsViewModel.image)
        holder.coursename.text = itemsViewModel.name
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val courseimage: ImageView = itemView.findViewById(R.id.course_pic)
        val coursename: TextView = itemView.findViewById(R.id.course_name)

    }
}
package com.example.tringconnectapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tringconnectapp.R
import com.example.tringconnectapp.data.Items

class CustomAdapter(private val mList: List<Items>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_scroll, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        if (position == 0) {
            holder.imageView.setImageResource(R.drawable.ic_plus_with_circle_icon_plus_with_circle2x)
        } else {
            holder.imageView.setImageResource(itemsViewModel.image)
        }
        holder.names.text = itemsViewModel.name

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.scroll_icon)
        val names: TextView = itemView.findViewById(R.id.profile_text)
    }
}
package com.example.apod.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.apod.R
import com.example.apod.entity.ApodDb
import com.example.apod.utils.ApodDiffUtilCallback
import kotlinx.android.synthetic.main.apod_rec_list_item.view.*

class ApodListAdapter(val glide: RequestManager, val listner: ApodClickListner) : PagedListAdapter<ApodDb, ApodViewHolder>(ApodDiffUtilCallback()){

    interface ApodClickListner{
        fun onApodClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apod_rec_list_item, parent, false)
        return ApodViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val item = getItem(position)
        item?.url.let { glide.load(it).into(holder.itemView.apodImageView) }
        holder.itemView.apodTitleTxt.text = item?.title
        holder.itemView.apodDateTxt.text = item?.date.toString()
        holder.itemView.setOnClickListener { item?.let { it1 -> listner.onApodClick(position) } }
    }

}

class ApodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
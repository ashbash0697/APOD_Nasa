package com.example.apod.apoddetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.apod.R
import com.example.apod.entity.ApodDb
import com.example.apod.utils.ApodDiffUtilCallback
import kotlinx.android.synthetic.main.apod_dtl_item.view.*

class ApodDtlAdapter (val glide: RequestManager): PagedListAdapter<ApodDb,ApodDtlViewHolder>(ApodDiffUtilCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodDtlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apod_dtl_item, parent, false)
        return ApodDtlViewHolder(view)

    }

    override fun onBindViewHolder(holder: ApodDtlViewHolder, position: Int) {
        val item = getItem(position)
        item?.url.let { glide.load(it).into(holder.itemView.apodDtlImage) }
        holder.itemView.apodTitle.text = item?.title
        holder.itemView.apodDesc.text = item?.explanation
        holder.itemView.apodDate.text = item?.date.toString()
    }

}

class ApodDtlViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
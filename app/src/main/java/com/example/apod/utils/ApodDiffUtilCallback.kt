package com.example.apod.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.apod.entity.ApodDb

class ApodDiffUtilCallback : DiffUtil.ItemCallback<ApodDb>(){
    override fun areItemsTheSame(oldItem: ApodDb, newItem: ApodDb): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ApodDb, newItem: ApodDb): Boolean {
        return oldItem.explanation == newItem.explanation
                && oldItem.copyright == newItem.copyright
                && oldItem.title == newItem.title
                && oldItem.url == oldItem.url
    }

}
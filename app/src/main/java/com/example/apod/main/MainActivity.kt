package com.example.apod.main

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apod.*
import com.example.apod.apoddetail.ApodDetailActivity
import com.example.apod.entity.ApodDb
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ApodListAdapter.ApodClickListner {



    private val mainVm by lazy { ViewModelProviders.of(this,
        ApodViewModelFactory(application as APODApllication)
    ).get(MainActivityViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ApodListAdapter(Glide.with(this), this)
        apodRecView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        apodRecView.adapter = adapter

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        val liveData = mainVm.getPagedApods(config).build()

        liveData.observe(this, Observer<PagedList<ApodDb>>{
           pagedList ->
            adapter.submitList(pagedList)
        })

    }

    override fun onApodClick(position: Int) {
        startActivity(Intent(this, ApodDetailActivity::class.java).putExtra("POSITION", position))
    }
}

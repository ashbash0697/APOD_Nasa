package com.example.apod.apoddetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.apod.APODApllication
import com.example.apod.ApodViewModelFactory
import com.example.apod.R
import com.example.apod.entity.ApodDb
import kotlinx.android.synthetic.main.activity_apod_detail.*

class ApodDetailActivity : AppCompatActivity() {

    private val apodDtlVm by lazy { ViewModelProviders.of(this, ApodViewModelFactory(application as APODApllication)).get(ApodDtlViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod_detail)
        val adapter = ApodDtlAdapter(Glide.with(this))
        apodDtlRec.adapter = adapter
        apodDtlRec.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL, false)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()


        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(apodDtlRec)

        val liveData = apodDtlVm.getPagedApods(config).build()

        liveData.observe(this, Observer<PagedList<ApodDb>> {
                pagedList ->
            (apodDtlRec.layoutManager as LinearLayoutManager).scrollToPosition(intent.extras.getInt("POSITION"))
            adapter.submitList(pagedList) })


    }
}

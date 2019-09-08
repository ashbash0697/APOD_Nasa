package com.example.apod

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apod.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainVm by lazy { ViewModelProviders.of(this, ApodViewModelFactory(application as APODApllication)).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //apodRecView.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, true)
        apodRecView.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, true)
        mainVm.getApods()



    }
}

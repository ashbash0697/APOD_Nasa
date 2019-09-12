package com.example.apod

import android.app.Application
import android.util.Log
import androidx.paging.PagedList
import com.example.apod.di.components.ApodComponent
import com.example.apod.entity.ApodDb
import com.example.apod.repository.ApodRepo
import com.example.apod.utils.PagingRequestHelper
import org.joda.time.LocalDate
import java.util.concurrent.Executors
import javax.inject.Inject

class ApodBoundaryCallback(): PagedList.BoundaryCallback<ApodDb>(), ApodComponent.Injectable{
    override fun inject(apodComponent: ApodComponent) {
        apodComponent.inject(this)
    }

    init {
        APODApllication.apodApllication.apodComponent.inject(this)
    }

    @Inject
    lateinit var repo: ApodRepo

    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL){
           helperCallback ->
            executor.execute {

                repo.getApods(LocalDate.now(), LocalDate.now().minusDays(20))

                helperCallback.recordSuccess()

            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: ApodDb) {
        super.onItemAtEndLoaded(itemAtEnd)

        Log.e("itemAt end", itemAtEnd.title);

                repo.getApods(itemAtEnd.date.minusDays(1), itemAtEnd.date.minusDays(21))


    }
}
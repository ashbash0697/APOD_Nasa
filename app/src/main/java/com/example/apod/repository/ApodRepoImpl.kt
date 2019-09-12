package com.example.apod.repository

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.apod.database.ApodDatabase
import com.example.apod.entity.ApodDb
import com.example.apod.network.ApodApi
import com.example.apod.network.ApodService
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.util.*

class ApodRepoImpl(val apodDatabase: ApodDatabase,val apodService: ApodService): ApodRepo{


    override fun getPagedApods(): DataSource.Factory<Int, ApodDb> {
      return  apodDatabase.apodDao().getApodPaged()
    }

    val JSON_DATE_FRMT = DateTimeFormat.forPattern("yyyy-MM-dd")


    override fun getApods(now: LocalDate?, endDate: LocalDate?): LiveData<List<ApodDb>>{

       val dateIterator = DateRange(endDate!!, now!!).iterator()
        dateIterator.forEach { localDate ->
            getApodFromApiOrRoom(localDate)
        }

        return getAllApod()
    }

    private fun getAllApod(): LiveData<List<ApodDb>> {
        return apodDatabase.apodDao().getApod()
    }

    private fun getApodFromApiOrRoom(localDate: LocalDate) {

        val item1 = apodDatabase.apodDao().getApod(localDate)
        val item2 = apodService.getApod(date = localDate.toString(JSON_DATE_FRMT))
            .toFlowable()
            .doOnNext { apodApi: ApodApi ->  apodDatabase.apodDao().addApod(ApodDb(date = LocalDate.parse(apodApi.date),
                title = apodApi.title, url = apodApi.url, hdurl = apodApi.hdurl, media_type = apodApi.media_type, copyright = apodApi.copyright, explanation = apodApi.explanation))}

        item1.concatMap { if (it.isEmpty()) item2 else Flowable.just(it) }.subscribe()


    }



}



class DateRange(override val start: LocalDate, override val endInclusive: LocalDate) : ClosedRange<LocalDate>, Iterable<LocalDate> {
    override fun iterator(): Iterator<LocalDate> {
        return DateIterator(start, endInclusive)
    }
}

class DateIterator(start: LocalDate,val endInclusive: LocalDate): Iterator<LocalDate>{

    private var current = start

    override fun hasNext(): Boolean {
        return current < endInclusive
    }

    override fun next(): LocalDate {
        current = current.plusDays(1)
        return current
    }

}
package com.example.apod.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.apod.database.ApodDatabase
import com.example.apod.entity.ApodDb
import com.example.apod.network.ApodService
import org.joda.time.LocalDate

class ApodRepoImpl(val apodDatabase: ApodDatabase, apodService: ApodService): ApodRepo{


    override fun getApods(now: LocalDate?, endDate: LocalDate?){


            for( e in DateRange(now!!, endDate!!).iterator())
            {
                Log.e("date", e.toString())
            }


    }


}

class DateRange(override val start: LocalDate, override val endInclusive: LocalDate) : ClosedRange<LocalDate>, Iterable<LocalDate> {
    override fun iterator(): Iterator<LocalDate> {
        return DateIterator(start, endInclusive)
    }
}

class DateIterator(start: LocalDate,val endInclusive: LocalDate): Iterator<LocalDate>{

    private var current = start;

    override fun hasNext(): Boolean {
        return current <= endInclusive
    }

    override fun next(): LocalDate {
        current = current.plusDays(1)
        return current
    }

}
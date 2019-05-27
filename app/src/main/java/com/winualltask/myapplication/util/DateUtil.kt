package com.winualltask.myapplication.util

import java.text.DateFormat
import java.util.*

object DateUtil {

    fun checkIfGreaterThanDay(timeInMills:Long):Boolean{
        val millsForDay = 60*60*24*1000
        val currentMills = Calendar.getInstance().timeInMillis
        return (currentMills-timeInMills)>millsForDay
    }

    fun getDateString(timeInMills: Long):String{
        val sdf = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT, Locale.getDefault())
        return sdf.format(Date(timeInMills))
    }
}
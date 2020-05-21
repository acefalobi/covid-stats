package com.aceinteract.topcoder.covidstats.data.remote.mapper

import com.aceinteract.topcoder.covidstats.util.Constants
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

interface RemoteModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

    fun mapModelList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapFromModel(it))
        }

        return list
    }

    fun safeIntParse(string: String): Int {
        return try {
            string.replace(",", "").toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    fun safeFloatParse(string: String): Float {
        return try {
            string.replace(",", "").toFloat()
        } catch (e: NumberFormatException) {
            0F
        }
    }

    fun safeString(string: String?): String {
        return string ?: "N/A"
    }

    fun safeParse(from: String): Date {
        return SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).parse(from)!!
    }

}
package com.aceinteract.topcoder.covidstats.data.local.mapper

import java.text.SimpleDateFormat
import java.util.*

interface LocalModelMapper<M, E> {

    fun mapToEntity(model: M): E

    fun mapToModel(entity: E): M

    fun mapToEntityList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapToEntity(it))
        }

        return list
    }

    fun mapToModelList(models: List<E>?): List<M> {
        val list = mutableListOf<M>()
        models?.forEach {
            list.add(mapToModel(it))
        }

        return list
    }

    fun safeString(string: String?): String {
        return string ?: "N/A"
    }

    fun safeBoolean(boolean: Boolean?): Boolean {
        return boolean ?: false
    }

    fun safeParse(from: String): Date {
        return SimpleDateFormat("MMM, d yyyy HH:mm, z", Locale.getDefault()).parse(from)!!
    }

}
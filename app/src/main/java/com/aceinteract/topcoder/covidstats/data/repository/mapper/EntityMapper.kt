package com.aceinteract.topcoder.covidstats.data.repository.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E

    fun mapFromEntityList(entities: List<E>): List<D> {
        val domainModels = mutableListOf<D>()
        entities.forEach {
            domainModels.add(mapFromEntity(it))
        }

        return domainModels
    }


    fun mapFromDomainList(domainModels: List<D>): List<E> {
        val entities = mutableListOf<E>()
        domainModels.forEach {
            entities.add(mapToEntity(it))
        }

        return entities
    }

}
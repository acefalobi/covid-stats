package com.aceinteract.topcoder.covidstats.data.repository.mapper

import com.aceinteract.topcoder.covidstats.data.repository.model.PaginationMetaEntity
import com.aceinteract.topcoder.covidstats.domain.model.PaginationMeta
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaginationMetaEntityMapper @Inject constructor() :
    EntityMapper<PaginationMetaEntity, PaginationMeta> {

    override fun mapFromEntity(entity: PaginationMetaEntity): PaginationMeta {
        return PaginationMeta(
            entity.currentPage,
            entity.currentPageSize,
            entity.totalPages,
            entity.totalRecords

        )
    }

    override fun mapToEntity(domain: PaginationMeta): PaginationMetaEntity {
        return PaginationMetaEntity(
            domain.currentPage,
            domain.currentPageSize,
            domain.totalPages,
            domain.totalRecords
        )
    }

}
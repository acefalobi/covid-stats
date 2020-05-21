package com.aceinteract.topcoder.covidstats.data.remote.mapper

import com.aceinteract.topcoder.covidstats.data.remote.models.PaginationMetaRemote
import com.aceinteract.topcoder.covidstats.data.repository.model.PaginationMetaEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaginationMetaMapper @Inject constructor() :
    RemoteModelMapper<PaginationMetaRemote, PaginationMetaEntity> {

    override fun mapFromModel(model: PaginationMetaRemote): PaginationMetaEntity {
        return PaginationMetaEntity(
            model.currentPage,
            model.currentPageSize,
            model.totalPages,
            model.totalRecords

        )
    }

}
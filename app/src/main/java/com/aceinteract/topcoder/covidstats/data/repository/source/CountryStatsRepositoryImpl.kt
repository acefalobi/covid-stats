package com.aceinteract.topcoder.covidstats.data.repository.source

import com.aceinteract.topcoder.covidstats.data.repository.local.ICountryStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.mapper.CountryStatsEntityMapper
import com.aceinteract.topcoder.covidstats.data.repository.mapper.CountryStatsSetEntityMapper
import com.aceinteract.topcoder.covidstats.data.repository.remote.ICountryStatsRemote
import com.aceinteract.topcoder.covidstats.domain.model.CountryStatsSet
import com.aceinteract.topcoder.covidstats.domain.model.PaginationMeta
import com.aceinteract.topcoder.covidstats.domain.repository.ICountryStatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryStatsRepositoryImpl @Inject constructor(
    private val countryStatsLocal: ICountryStatsLocal,
    private val countryStatsRemote: ICountryStatsRemote,
    private val countryStatsEntityMapper: CountryStatsEntityMapper,
    private val countryStatsSetEntityMapper: CountryStatsSetEntityMapper
) : ICountryStatsRepository {

    override fun getCountryStats(
        shouldRefresh: Boolean,
        page: Int,
        search: String?
    ): Flow<CountryStatsSet> = flow {
        var localData =
            if (search != null) countryStatsLocal.getCountryStats(search) else countryStatsLocal.getCountryStats()
        if (!shouldRefresh && localData.isNotEmpty() && page == 1) {
            emit(
                CountryStatsSet(
                    localData[0].lastUpdate,
                    PaginationMeta(1, localData.size, 1, localData.size),
                    countryStatsEntityMapper.mapFromEntityList(localData)
                )
            )
        } else {
            val remoteData = countryStatsRemote.getCountryStats(page)
            countryStatsLocal.insertCountryStats(remoteData.rows)
            if (remoteData.paginationMeta.currentPage == remoteData.paginationMeta.totalPages) {
                localData = countryStatsLocal.getCountryStats()
                emit(
                    CountryStatsSet(
                        localData[0].lastUpdate,
                        PaginationMeta(1, localData.size, 1, localData.size),
                        countryStatsEntityMapper.mapFromEntityList(localData)
                    )
                )
            } else {
                emit(
                    countryStatsSetEntityMapper.mapFromEntity(remoteData)
                )
            }

        }
    }

}
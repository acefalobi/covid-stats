package com.aceinteract.topcoder.covidstats.data.local.source

import com.aceinteract.topcoder.covidstats.data.local.mapper.CountryStatsLocalMapper
import com.aceinteract.topcoder.covidstats.data.local.room.dao.CountryStatsDao
import com.aceinteract.topcoder.covidstats.data.repository.local.ICountryStatsLocal
import com.aceinteract.topcoder.covidstats.data.repository.model.CountryStatsEntity
import javax.inject.Inject

class CountryStatsLocalSource @Inject constructor(
    private val countryStatsDao: CountryStatsDao,
    private val countryStatsLocalMapper: CountryStatsLocalMapper
) : ICountryStatsLocal {

    override suspend fun getCountryStats(): List<CountryStatsEntity> {
        return countryStatsLocalMapper.mapToEntityList(countryStatsDao.getCountryStats())
    }

    override suspend fun getCountryStats(query: String): List<CountryStatsEntity> {
        return countryStatsLocalMapper.mapToEntityList(countryStatsDao.getCountryStats("%$query%"))
    }

    override suspend fun insertCountryStats(stats: List<CountryStatsEntity>) {
        countryStatsDao.insertCountryStats(countryStatsLocalMapper.mapToModelList(stats))
    }


}
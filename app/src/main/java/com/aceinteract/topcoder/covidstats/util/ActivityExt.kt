package com.aceinteract.topcoder.covidstats.util

import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.aceinteract.topcoder.covidstats.R
import com.aceinteract.topcoder.covidstats.domain.model.CountryStats
import com.aceinteract.topcoder.covidstats.ui.main.countrystats.CountryStatsBottomSheetFragment
import com.aceinteract.topcoder.covidstats.view.PieChartView
import java.text.SimpleDateFormat
import java.util.*


fun AppCompatActivity.showBottomSheet(stats: CountryStats) {
    val menuFragment = CountryStatsBottomSheetFragment {
        textLastUpdated.text = SimpleDateFormat(
            Constants.DATE_FORMAT,
            Locale.getDefault()
        ).format(stats.lastUpdate)
        imageFlag.load(stats.flag)
        textCountryName.text = stats.country
        textTotalCases.text = "%,d".format(stats.totalCases)
        textInfected.text = if (stats.activeCases != 0) "%,d".format(stats.activeCases) else "N/A"
        textRecovered.text = if (stats.totalRecovered != 0) "%,d".format(stats.totalRecovered) else "N/A"
        textDeaths.text = if (stats.totalDeaths != 0) "%,d".format(stats.totalDeaths) else "N/A"

        pieGeneralStats.piePieces = listOf(
            PieChartView.PiePiece(
                stats.activeCases.toDouble(),
                getColorCompat(R.color.color_warning)
            ),
            PieChartView.PiePiece(
                stats.totalRecovered.toDouble(),
                getColorCompat(R.color.color_good)
            ),
            PieChartView.PiePiece(
                stats.totalDeaths.toDouble(),
                getColorCompat(R.color.color_danger)
            )
        )
    }

    menuFragment.show(supportFragmentManager, menuFragment.tag)
}
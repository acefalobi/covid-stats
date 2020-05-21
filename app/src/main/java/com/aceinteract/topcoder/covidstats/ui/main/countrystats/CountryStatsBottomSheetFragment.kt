package com.aceinteract.topcoder.covidstats.ui.main.countrystats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aceinteract.topcoder.covidstats.databinding.CountryStatsSheetBinding
import com.aceinteract.topcoder.covidstats.ui.base.RoundedBottomSheetDialogFragment

class CountryStatsBottomSheetFragment(private val onCreated: CountryStatsSheetBinding.() -> Unit) :
    RoundedBottomSheetDialogFragment() {

    lateinit var viewBinding: CountryStatsSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = CountryStatsSheetBinding.inflate(inflater, container, false)

        viewBinding.buttonClose.setOnClickListener {
            dismiss()
        }

        onCreated(viewBinding)

        return viewBinding.root
    }

}
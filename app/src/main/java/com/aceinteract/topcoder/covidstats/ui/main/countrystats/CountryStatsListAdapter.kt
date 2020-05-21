package com.aceinteract.topcoder.covidstats.ui.main.countrystats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.request.CachePolicy
import com.aceinteract.topcoder.covidstats.databinding.CountryStatsListItemBinding
import com.aceinteract.topcoder.covidstats.domain.model.CountryStats
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CountryStatsListAdapter(private val onClickListener: (countryStats: CountryStats) -> Unit) :
    ListAdapter<CountryStats, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryStatsListItemBinding.inflate(inflater, parent, false)
        return CountryStatsViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as CountryStatsViewHolder).bind(getItem(position))
    }

    class CountryStatsViewHolder(
        private val binding: CountryStatsListItemBinding,
        private val onClickListener: (countryStats: CountryStats) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stats: CountryStats) {

            binding.root.setOnClickListener { onClickListener(stats) }
//            GlobalScope.launch {
                binding.imageFlag.load(stats.flag) {
                    diskCachePolicy(CachePolicy.ENABLED)
                    memoryCachePolicy(CachePolicy.ENABLED)
                }
//            }

            binding.textCountryName.text = stats.country
            binding.textTotalCases.text = "%,d".format(stats.totalCases)
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<CountryStats>() {
        override fun areItemsTheSame(oldItem: CountryStats, newItem: CountryStats): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: CountryStats, newItem: CountryStats): Boolean {
            return oldItem.country == newItem.country
        }
    }
}
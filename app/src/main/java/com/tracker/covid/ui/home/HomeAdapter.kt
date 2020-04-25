package com.tracker.covid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tracker.covid.R
import com.tracker.covid.data.remote.model.Country
import kotlinx.android.synthetic.main.home_item.view.*

class HomeAdapter(private val allItems: List<Country>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return HomeViewHolder(inflater, parent.context)
    }

    override fun getItemCount(): Int = allItems.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(allItems[position])
    }

    class HomeViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val countryName = view.countryName
        private val countryCode = view.flag
        private val newInfected = view.newInfected
        private val totalInfected = view.totalInfected
        private val newRecovery = view.newRecover
        private val totalRecovery = view.totalRecover
        private val newDeaths = view.newDeaths
        private val totalDeath = view.totalDeaths

        fun bind(items: Country) {
            countryName.text = items.countryName

            newInfected.text = items.newConfirmedCases.toString()
            totalInfected.text = items.totalConfirmedCases.toString()

            newRecovery.text = items.newRecoveredCases.toString()
            totalRecovery.text = items.totalRecoveredCases.toString()

            newDeaths.text = items.newDeathCases.toString()
            totalDeath.text = items.totalDeathCases.toString()
        }
    }

}
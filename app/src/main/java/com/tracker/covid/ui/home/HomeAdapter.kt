package com.tracker.covid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tracker.covid.R
import com.tracker.covid.data.remote.model.Country
import com.tracker.covid.data.remote.response.GlobalSummary
import kotlinx.android.synthetic.main.home_item.view.*

class HomeAdapter(private val allItems: List<Country>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return HomeViewHolder(inflater)
    }

    override fun getItemCount(): Int = allItems.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(allItems[position])
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val hCountryName: TextView = view.countryName
        fun bind(items: Country) {
            hCountryName.text = items.countryName
        }
    }

}
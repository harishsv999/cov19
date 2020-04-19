package com.tracker.covid.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tracker.covid.R
import com.tracker.covid.data.remote.model.CountryCodes
import kotlinx.android.synthetic.main.raw_countries.view.*

class CountriesAdapter(private val countiesList: List<CountryCodes>) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.raw_countries, parent, false)
        return CountryViewHolder(inflater)
    }

    override fun getItemCount(): Int = countiesList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countiesList[position].countryName)
    }

    /**
     * ViewHolder class for each row item.
     */
    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCountryName: TextView = view.country_name
        private val imageViewFlag: ImageView = view.iv_flag

        fun bind(countryName: String?) {
            tvCountryName.text = countryName
            // imageViewFlag.sourc = currency.toString()
        }
    }


}

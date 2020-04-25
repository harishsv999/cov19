package com.tracker.covid.ui.countries

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tracker.covid.R
import com.tracker.covid.data.remote.model.CountryCodes
import com.tracker.covid.ui.base.setImageURL
import kotlinx.android.synthetic.main.countries_item.view.*

class CountriesAdapter(private val countiesList: List<CountryCodes>) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.countries_item, parent, false)
        return CountryViewHolder(inflater)
    }

    override fun getItemCount(): Int = countiesList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countiesList[position])
    }

    /**
     * ViewHolder class for each row item.
     */
    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCountryName: TextView = view.countryName
        private val imageViewFlag: ImageView = view.flag

        fun bind(codes: CountryCodes) {
            val URL = "https://www.countryflags.io/${codes.countryCode}/flat/64.png";

            tvCountryName.text = codes.countryName
            imageViewFlag.setImageURL(URL)
        }
    }


}

package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igor.bykov.skyscannerapp.R
import com.igor.bykov.skyscannerapp.data.flight.model.Leg
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel
import kotlinx.android.synthetic.main.item_flight.view.*
import kotlinx.android.synthetic.main.item_flight_details.view.*

class FlightViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  fun bind(flightViewModel: FlightViewModel?) {
    flightViewModel?.firstFlight?.carrierLogoUrl?.let {
      Glide.with(itemView.context).load(it).into(itemView.firstFlight.carrierLogo)
    }
    itemView.firstFlight.time.text = flightViewModel?.firstFlight?.time

    flightViewModel?.secondFlight?.carrierLogoUrl?.let {
      Glide.with(itemView.context).load(it).into(itemView.secondFlight.carrierLogo)
    }
    itemView.secondFlight.time.text = flightViewModel?.secondFlight?.time

    itemView.price.text = flightViewModel?.price
  }

  companion object {
    fun create(parent: ViewGroup): FlightViewHolder {
      val view = LayoutInflater.from(parent.context)
              .inflate(R.layout.item_flight, parent, false)
      return FlightViewHolder(view)
    }
  }
}
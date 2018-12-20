package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.igor.bykov.skyscannerapp.data.flight.model.Leg
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.State
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel

class FlightsAdapter : PagedListAdapter<FlightViewModel, RecyclerView.ViewHolder>(FlightDiffCallback) {

  private val DATA_VIEW_TYPE = 1
  private val FOOTER_VIEW_TYPE = 2

  private var state = State.LOADING

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return if (viewType == DATA_VIEW_TYPE) FlightViewHolder.create(parent) else FooterViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (getItemViewType(position) == DATA_VIEW_TYPE)
      (holder as FlightViewHolder).bind(getItem(position))
    else (holder as FooterViewHolder).bind(state)
  }

  override fun getItemViewType(position: Int): Int {
    return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
  }

  override fun getItemCount(): Int {
    return super.getItemCount() + if (hasFooter()) 1 else 0
  }

  private fun hasFooter(): Boolean {
    return super.getItemCount() != 0 && state == State.LOADING
  }

  fun setState(state: State) {
    this.state = state
    notifyItemChanged(super.getItemCount())
  }

  companion object {
    val FlightDiffCallback = object : DiffUtil.ItemCallback<FlightViewModel>() {
      override fun areItemsTheSame(oldItem: FlightViewModel, newItem: FlightViewModel): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: FlightViewModel, newItem: FlightViewModel): Boolean {
        return oldItem == newItem
      }
    }
  }
}
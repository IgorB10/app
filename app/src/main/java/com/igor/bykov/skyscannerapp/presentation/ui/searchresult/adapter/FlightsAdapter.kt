package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.igor.bykov.skyscannerapp.R
import com.igor.bykov.skyscannerapp.data.flight.model.Flight

class FlightsAdapter() : PagedListAdapter<Flight, RecyclerView.ViewHolder>(POST_COMPARATOR) {


  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (getItemViewType(position)) {
      R.layout.reddit_post_item -> (holder as RedditPostViewHolder).bind(getItem(position))
      R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindTo(
          networkState)
    }
  }

  override fun onBindViewHolder(
      holder: RecyclerView.ViewHolder,
      position: Int,
      payloads: MutableList<Any>) {
    if (payloads.isNotEmpty()) {
      val item = getItem(position)
      (holder as RedditPostViewHolder).updateScore(item)
    } else {
      onBindViewHolder(holder, position)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      R.layout.reddit_post_item -> RedditPostViewHolder.create(parent, glide)
      R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent, retryCallback)
      else -> throw IllegalArgumentException("unknown view type $viewType")
    }
  }

  private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

  override fun getItemViewType(position: Int): Int {
    return if (hasExtraRow() && position == itemCount - 1) {
      R.layout.network_state_item
    } else {
      R.layout.reddit_post_item
    }
  }

  override fun getItemCount(): Int {
    return super.getItemCount() + if (hasExtraRow()) 1 else 0
  }

  fun setNetworkState(newNetworkState: NetworkState?) {
    val previousState = this.networkState
    val hadExtraRow = hasExtraRow()
    this.networkState = newNetworkState
    val hasExtraRow = hasExtraRow()
    if (hadExtraRow != hasExtraRow) {
      if (hadExtraRow) {
        notifyItemRemoved(super.getItemCount())
      } else {
        notifyItemInserted(super.getItemCount())
      }
    } else if (hasExtraRow && previousState != newNetworkState) {
      notifyItemChanged(itemCount - 1)
    }
  }

  companion object {

    val POST_COMPARATOR = object : DiffUtil.ItemCallback<Flight>() {
      override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean =
          oldItem == newItem

      override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean =
          oldItem.login == newItem.login

    }

    private fun sameExceptScore(oldItem: Flight, newItem: Flight): Boolean {
      // DON'T do this copy in a real app, it is just convenient here for the demo :)
      // because reddit randomizes scores, we want to pass it as a payload to minimize
      // UI updates between refreshes
      return oldItem == newItem
    }
  }
}
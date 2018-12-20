package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igor.bykov.skyscannerapp.R
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.State
import kotlinx.android.synthetic.main.item_footer.view.*

class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  fun bind(status: State?) {
    itemView.progress_bar.visibility = if (status == State.LOADING) VISIBLE else View.INVISIBLE
  }

  companion object {
    fun create(parent: ViewGroup): FooterViewHolder {
      val view = LayoutInflater.from(parent.context)
              .inflate(R.layout.item_footer, parent, false)
      return FooterViewHolder(view)
    }
  }
}
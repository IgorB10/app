package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.igor.bykov.skyscannerapp.R
import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.presentation.ui.BaseActivity
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.adapter.FlightsAdapter
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.event.UIOpenFlightDetailsEvent
import kotlinx.android.synthetic.main.activity_search_result.*
import org.greenrobot.eventbus.Subscribe
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class SearchResultActivity : BaseActivity(), KodeinAware {

  override val kodein: Kodein by closestKodein()

  private lateinit var viewModel: SearchResultViewModel
  private lateinit var adapter: FlightsAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search_result)
    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayShowTitleEnabled(false)

    viewModel = ViewModelProvider(this, SearchResultViewModelFactory(kodein)).get(SearchResultViewModel::class.java)

    initAdapter()
    initState()
  }

  private fun initAdapter() {
    adapter = FlightsAdapter()
    searchResultRv.layoutManager = LinearLayoutManager(this)
    searchResultRv.adapter = adapter
    viewModel.newsList.observe(this, Observer {
      adapter.submitList(it)
    })
  }

  private fun initState() {
    viewModel.getState().observe(this, Observer { state ->
      if (!viewModel.listIsEmpty()) {
        adapter.setState(state ?: State.DONE)
      }
    })
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.search_result, menu)
    return true
  }

  @Subscribe
  fun onUIOpenFlightDetailsEvent(event: UIOpenFlightDetailsEvent) {
    startActivity(Intent(Intent.ACTION_VIEW, event.url.toUri()))
  }
}

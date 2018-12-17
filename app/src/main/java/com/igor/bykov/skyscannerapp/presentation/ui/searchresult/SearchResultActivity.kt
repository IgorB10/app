package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.LifecycleObserver
import com.igor.bykov.skyscannerapp.R
import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.presentation.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_search_result.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SearchResultActivity : BaseActivity(), SearchResultView, KodeinAware {

  override val kodein: Kodein by closestKodein()

  private val presenter: SearchResultPresenter by instance()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search_result)
    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayShowTitleEnabled(false)


    presenter.bind(this)
    presenter.fetchFlights()

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.search_result, menu)
    return true
  }

  override fun renderFlights(flights: FlightResponse) {

  }

  override fun presenter(): LifecycleObserver? {
    return presenter
  }


}

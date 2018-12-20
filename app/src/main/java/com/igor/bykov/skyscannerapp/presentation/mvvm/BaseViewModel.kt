package com.igor.bykov.skyscannerapp.presentation.mvvm


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
/**
 * Created by work on 11/11/17.
 */

open class BaseViewModel : ViewModel(), CoroutineScope {

  private val jobDelegate = lazy { Job() }
  private val job: Job by jobDelegate

  override fun onCleared() {
    super.onCleared()
    if(jobDelegate.isInitialized()) {
      job.cancel()
    }
  }

  override val coroutineContext = job + Dispatchers.Main
}

package com.igor.bykov.skyscannerapp.presentation.mvp


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
/**
 * Created by work on 11/11/17.
 */

open class BasePresenter<V : MvpView> : LifecycleObserver, CoroutineScope {

  private val jobDelegate = lazy { Job() }
  private val job: Job by jobDelegate

  protected lateinit var view: V

  fun bind(view: V) {
    this.view = view
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun destroy() {
    if(jobDelegate.isInitialized()) {
      job.cancel()
    }
  }

  override val coroutineContext = job + Dispatchers.Main
}

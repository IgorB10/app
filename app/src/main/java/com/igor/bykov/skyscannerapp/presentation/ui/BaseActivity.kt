package com.igor.bykov.skyscannerapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver

abstract class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter()?.let {
      lifecycle.addObserver(it)
    }
  }

  abstract fun presenter(): LifecycleObserver?
}
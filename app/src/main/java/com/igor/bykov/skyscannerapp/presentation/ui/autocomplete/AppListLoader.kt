package com.igor.bykov.skyscannerapp.presentation.ui.autocomplete

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class AppListLoader(context: Context) : AsyncTaskLoader<String>(context) {

  override fun loadInBackground(): String? {
    return ""
  }
}
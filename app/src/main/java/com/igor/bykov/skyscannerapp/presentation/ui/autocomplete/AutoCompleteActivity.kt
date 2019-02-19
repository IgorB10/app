package com.igor.bykov.skyscannerapp.presentation.ui.autocomplete

import android.app.IntentService
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.igor.bykov.skyscannerapp.R
import com.igor.bykov.skyscannerapp.presentation.ui.BaseActivity
import kotlinx.android.synthetic.main.text_act.*
import org.kodein.di.weakReference
import java.lang.ref.WeakReference

class AutoCompleteActivity : AppCompatActivity(), TextWatcher {

  val handler = Handler()

  var curr: SendTextRubnnable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.text_act)
    input.addTextChangedListener(this)
  }

  override fun afterTextChanged(s: Editable?) {
  }

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    curr?.let {
      handler.removeCallbacks(it)
    }
    curr = SendTextRubnnable(WeakReference(text), s.toString())
    handler.postDelayed(curr, 1500)
  }

  class SendTextRubnnable(val weakRef: WeakReference<TextView>, val text: String) : Runnable {

    override fun run() {
      if (weakRef.get() != null) {
        (weakRef.get() as TextView).text = text
      }
    }
  }
}
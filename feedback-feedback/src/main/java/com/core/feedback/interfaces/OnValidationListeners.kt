package com.core.feedback.interfaces

import android.text.Editable
import android.text.TextWatcher
import android.widget.RatingBar
import android.widget.TextView

interface OnValidationListeners {

    fun attachListeners(tv: TextView, rb: RatingBar) {
        addTvTextListener(tv)
        rb.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, _, _ -> fieldChanged() }
    }

    fun attachListeners(tv1: TextView, tv2: TextView) {
        addTvTextListener(tv1)
        addTvTextListener(tv2)
    }

    private fun addTvTextListener(tv: TextView) {
        tv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fieldChanged()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun fieldChanged()

}
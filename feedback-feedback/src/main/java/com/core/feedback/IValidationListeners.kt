package com.core.feedback

import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView

interface IValidationListeners {

    fun attachListeners(tv: TextView, rb: RatingBar? = null) {
        tv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fieldChanged()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        rb?.let {
            it.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { _, _, _ -> fieldChanged() }
        }
    }

    fun attachListeners(tv: TextView, rg: RadioGroup? = null) {
        tv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fieldChanged()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        rg?.setOnCheckedChangeListener { _, _ -> fieldChanged() }
    }

    // Override and add logic to validate fields
    fun fieldChanged()

}
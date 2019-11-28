package com.core.feedback

import android.text.Editable

class Validator {
    companion object {
        fun isBothFilled(rating: Float, text: Editable?): Boolean {
            if (ratingFilled(rating) && commentFilled(text)) {
                return true
            }
            return false
        }

        fun commentFilled(text: Editable?) = !text.isNullOrEmpty()

        fun ratingFilled(rating: Float) = rating > 0
    }
}
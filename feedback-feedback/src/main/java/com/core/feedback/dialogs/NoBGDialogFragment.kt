package com.core.feedback.dialogs

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment

abstract class NoBGDialogFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()

        val size = Point()
        activity?.windowManager?.defaultDisplay?.getSize(size)

        dialog?.window?.setLayout(
            (size.x - (view!!.context.resources.getDimension(com.core.feedback.R.dimen.feedback_default_padding) * 2)).toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, android.R.style.Theme)
    }
}
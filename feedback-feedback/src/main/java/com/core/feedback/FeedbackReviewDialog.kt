package com.core.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_feedback_review.view.*
import kotlinx.android.synthetic.main.dialog_feedback_partial_buttons.view.*
import kotlinx.android.synthetic.main.dialog_feedback_rating.view.*
import kotlinx.android.synthetic.main.dialog_feedback_review.view.dialog_fragment_feedback_et_review
import kotlinx.android.synthetic.main.dialog_feedback_review.view.dialog_fragment_feedback_title


class FeedbackReviewDialog(
    var title: String?,
    var reviewHint: String?,
    var photoBtnText: String?,
    var onPhotoBtnClickAction: () -> Unit,
    var okBtnText: String?,
    var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
    var closeBtnText: String?) : DialogFragment() {

    lateinit var photoBTN: Button

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_feedback_review, container, false)

        photoBTN = view.dialog_fragment_feedback_button_photo

        view.dialog_fragment_feedback_title.text = title
        photoBTN.text = photoBtnText
        view.dialog_fragment_feedback_et_review.hint = reviewHint
        view.dialog_fragment_feedback_button_send.text = okBtnText
        view.dialog_fragment_feedback_button_cancel.text = closeBtnText

        view.dialog_fragment_feedback_button_send.isEnabled = true

        val photoBTN = view.dialog_fragment_feedback_button_photo

        photoBTN.setOnOneClickListener {
            onPhotoBtnClickAction.invoke()
        }

        view.dialog_fragment_feedback_button_cancel.setOnOneClickListener { dismiss() }
        view.dialog_fragment_feedback_button_send.setOnOneClickListener {
            onOkBtnClickAction.invoke(0, view.dialog_fragment_feedback_et_review.text.toString())
            dismiss()
        }

        return view
    }

    fun setPhotoBTNText(text: String) {
        photoBTN.text = text
    }
}
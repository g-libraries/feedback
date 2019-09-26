package com.core.feedback.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.core.feedback.R
import com.core.feedback.setOnOneClickListener
import kotlinx.android.synthetic.main.dialog_feedback_partial_buttons.view.*
import kotlinx.android.synthetic.main.dialog_feedback.view.*


class FeedbackDialog(
    var title: String?,
    var ratingBtnText: String?,
    var reviewBtnText: String?,
    var onItemClickAction: (pos: Int) -> Unit,
    var closeBtnText: String?
) : NoBGDialogFragment() {
    companion object {
        const val REVIEW_DIALOG = 0
        const val RATING_DIALOG = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_feedback, container)

        view.dialog_fragment_feedback_title.text = title
        view.dialog_fragment_feedback_button_review.text = reviewBtnText
        view.dialog_fragment_feedback_button_rating.text = ratingBtnText
        view.dialog_fragment_feedback_button_cancel.text = closeBtnText

        view.dialog_fragment_feedback_button_review.setOnOneClickListener {
            dismiss()
            onItemClickAction.invoke(REVIEW_DIALOG)
        }
        view.dialog_fragment_feedback_button_rating.setOnOneClickListener {
            dismiss()
            onItemClickAction.invoke(RATING_DIALOG)
        }
        view.dialog_fragment_feedback_button_send.visibility = View.GONE
        view.dialog_fragment_feedback_button_cancel.setOnOneClickListener {
            dismiss()
        }

        return view

    }
}
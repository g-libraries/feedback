package com.core.feedback.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import com.core.feedback.R
import com.core.feedback.IValidationListeners
import com.core.feedback.setOnOneClickListener
import com.hanks.lineheightedittext.LineHeightEditText
import kotlinx.android.synthetic.main.dialog_feedback_partial_buttons.view.*
import kotlinx.android.synthetic.main.dialog_feedback_rating.view.*


class FeedbackRatingDialog(
    var title: String?,
    var reviewHint: String?,
    var ratingHint: String?,
    var okBtnText: String?,
    var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
    var closeBtnText: String?
) : NoBGDialogFragment(), IValidationListeners {

    private lateinit var reviewET: LineHeightEditText
    private lateinit var ratingRB: RatingBar
    private lateinit var sendBTN: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_feedback_rating, container)

        reviewET = view.dialog_fragment_feedback_et_review
        ratingRB = view.dialog_fragment_feedback_ratingbar
        sendBTN = view.dialog_fragment_feedback_button_send

        view.dialog_fragment_feedback_title.text = title
        reviewET.hint = reviewHint
        view.dialog_fragment_feedback_tv_rating_hint.text = ratingHint
        sendBTN.text = okBtnText
        view.dialog_fragment_feedback_button_cancel.text = closeBtnText

        attachListeners(view.dialog_fragment_feedback_et_review, ratingRB)

        view.dialog_fragment_feedback_button_cancel.setOnOneClickListener { dismiss() }

        view.dialog_fragment_feedback_button_send.setOnOneClickListener {
            onOkBtnClickAction.invoke(
                view.dialog_fragment_feedback_ratingbar.rating.toInt(),
                view.dialog_fragment_feedback_et_review.text.toString()
            )
            dismiss()
        }

        return view

    }

    override fun fieldChanged() {
        // Validation
        sendBTN.isEnabled = ratingRB.rating > 0 || reviewET.text?.isNotEmpty() ?: false
    }
}
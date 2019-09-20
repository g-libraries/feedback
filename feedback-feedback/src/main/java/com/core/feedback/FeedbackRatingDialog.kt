package com.core.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_feedback_partial_buttons.view.*
import kotlinx.android.synthetic.main.dialog_feedback_rating.view.*


class FeedbackRatingDialog(
    var title: String?,
    var reviewHint: String?,
//    var ratingStyleId: Int,
    var ratingHint: String?,
    var okBtnText: String?,
    var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
    var closeBtnText: String?
) : DialogFragment() {

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

        val view = inflater.inflate(R.layout.dialog_feedback_rating, container, false)

//        val ratingLayout = view.dialog_fragment_feedback_ll_rating
//        val contextThemeWrapper = ContextThemeWrapper(context, ratingStyleId)
//        val rating = RatingBar(contextThemeWrapper, null, 0)
//
//        ratingLayout.addView(rating)

        view.dialog_fragment_feedback_et_review.hint = reviewHint
        view.dialog_fragment_feedback_tv_rating_hint.text = ratingHint
        view.dialog_fragment_feedback_button_send.text = okBtnText
        view.dialog_fragment_feedback_button_cancel.text = closeBtnText

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
}
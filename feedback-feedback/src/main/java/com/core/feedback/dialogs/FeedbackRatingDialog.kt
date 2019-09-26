package com.core.feedback.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import com.core.feedback.R
import com.core.feedback.interfaces.OnValidationListeners
import com.core.feedback.interfaces.setOnOneClickListener
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
) : DialogFragment(), OnValidationListeners {

    private lateinit var reviewET: LineHeightEditText
    private lateinit var ratingRB: RatingBar
    private lateinit var sendBTN: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        dialog?.window?.setLayout(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, android.R.style.Theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_feedback_rating, container, false)

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
package com.core.feedback.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import com.core.feedback.R
import com.core.feedback.IValidationListeners
import com.core.feedback.setOnOneClickListener
import com.hanks.lineheightedittext.LineHeightEditText
import kotlinx.android.synthetic.main.dialog_feedback_partial_buttons.view.*
import kotlinx.android.synthetic.main.dialog_feedback_rating_smile.view.*

/**
 * Alternative Feedback dialog with smiles
 */
class FeedbackRatingSmileDialog(
    var title: String?,
    var reviewHint: String?,
    var ratingHint: String?,
    var okBtnText: String?,
    var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
    var closeBtnText: String?
) : NoBGDialogFragment(), IValidationListeners {

    private lateinit var reviewET: LineHeightEditText
    private lateinit var ratingRG: RadioGroup
    private lateinit var sendBTN: Button

    private var rating = -1 // -1 = Empty, 0 = negative, 1 = neutral, 2 = positive

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_feedback_rating_smile, container)

        reviewET = view.dialog_fragment_feedback_et_review
        ratingRG = view.dialog_fragment_feedback_ratingbar
        sendBTN = view.dialog_fragment_feedback_button_send

        view.dialog_fragment_feedback_title.text = title
        reviewET.hint = reviewHint
        view.dialog_fragment_feedback_tv_rating_hint.text = ratingHint
        sendBTN.text = okBtnText
        view.dialog_fragment_feedback_button_cancel.text = closeBtnText

        // init rating buttons listener
        view.dialog_fragment_feedback_button_negative.setOnOneClickListener { onRadioButtonClick }
        view.dialog_fragment_feedback_button_neutral.setOnOneClickListener { onRadioButtonClick }
        view.dialog_fragment_feedback_button_positive.setOnOneClickListener { onRadioButtonClick }

        attachListeners(view.dialog_fragment_feedback_et_review, ratingRG)

        view.dialog_fragment_feedback_button_cancel.setOnOneClickListener { dismiss() }

        view.dialog_fragment_feedback_button_send.setOnOneClickListener {
            onOkBtnClickAction.invoke(
                rating,
                view.dialog_fragment_feedback_et_review.text.toString()
            )
            dismiss()
        }

        // Last touch - set Positive rating as default
        view.dialog_fragment_feedback_button_positive.callOnClick()

        return view
    }

    private var onRadioButtonClick = { v: View ->
        when (v.id) {
            R.id.dialog_fragment_feedback_button_negative -> rating = 0
            R.id.dialog_fragment_feedback_button_neutral -> rating = 1
            R.id.dialog_fragment_feedback_button_positive -> rating = 2
        }
    }

    override fun fieldChanged() {
        // Validation
        sendBTN.isEnabled = rating > 0 || reviewET.text?.isNotEmpty() ?: false
    }
}
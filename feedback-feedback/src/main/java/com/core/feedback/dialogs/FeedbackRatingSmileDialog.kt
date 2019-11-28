package com.core.feedback.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import com.core.feedback.R
import com.core.feedback.IValidationListeners
import com.core.feedback.Validator
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
    var closeBtnText: String?,
    var ratingShouldBeFilled: Boolean = true,
    var commentShouldBeFilled: Boolean = true
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
        val negativeRB = view.dialog_fragment_feedback_button_negative
        val neutralRB = view.dialog_fragment_feedback_button_neutral
        val positiveRB = view.dialog_fragment_feedback_button_positive

        attachListeners(
            view.dialog_fragment_feedback_et_review,
            ratingRG,
            object : IValidationListeners.OnRadioGroupElementClickListener {
                override fun onClick(id: Int) {
                    when (id) {
                        R.id.dialog_fragment_feedback_button_negative -> rating = 0
                        R.id.dialog_fragment_feedback_button_neutral -> rating = 1
                        R.id.dialog_fragment_feedback_button_positive -> rating = 2
                    }
                }
            })

        view.dialog_fragment_feedback_button_cancel.setOnOneClickListener { dismiss() }

        view.dialog_fragment_feedback_button_send.setOnOneClickListener {
            onOkBtnClickAction.invoke(
                rating,
                view.dialog_fragment_feedback_et_review.text.toString()
            )
            dismiss()
        }

        // Check what Radio Button is checked by default if any
        when {
            negativeRB.isChecked -> {
                rating = 0
                fieldChanged()
            }
            neutralRB.isChecked -> {
                rating = 1
                fieldChanged()
            }
            positiveRB.isChecked -> {
                rating = 2
                fieldChanged()
            }
        }

        return view
    }

    override fun fieldChanged() {
        // Validation

        sendBTN.isEnabled = if (commentShouldBeFilled && ratingShouldBeFilled) {
            rating > -1 && Validator.commentFilled(reviewET.text)
        } else if (ratingShouldBeFilled) {
            rating > -1
        } else if (commentShouldBeFilled) {
            Validator.commentFilled(reviewET.text)
        } else {
            false
        }
    }
}
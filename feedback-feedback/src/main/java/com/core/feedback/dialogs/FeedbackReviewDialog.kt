package com.core.feedback.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.core.feedback.R
import com.core.feedback.IValidationListeners
import com.core.feedback.Validator
import com.core.feedback.setOnOneClickListener
import com.hanks.lineheightedittext.LineHeightEditText
import kotlinx.android.synthetic.main.dialog_feedback_review.view.*
import kotlinx.android.synthetic.main.dialog_feedback_partial_buttons.view.*
import kotlinx.android.synthetic.main.dialog_feedback_review.view.dialog_fragment_feedback_et_review
import kotlinx.android.synthetic.main.dialog_feedback_review.view.dialog_fragment_feedback_title


class FeedbackReviewDialog(
    var title: String?,
    var reviewHint: String?,
    var photoBtnText: String?,
    var onPhotoBtnClickAction: () -> Unit,
    var okBtnText: String?,
    var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
    var closeBtnText: String?,
    var imageShouldBeFilled: Boolean = true,
    var commentShouldBeFilled: Boolean = true
) : NoBGDialogFragment(), IValidationListeners {

    private lateinit var reviewET: LineHeightEditText
    private lateinit var photoBTN: Button
    private lateinit var sendBTN: Button
    private var imageSelected: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_feedback_review, container)

        photoBTN = view.dialog_fragment_feedback_button_photo
        sendBTN = view.dialog_fragment_feedback_button_send
        reviewET = view.dialog_fragment_feedback_et_review

        view.dialog_fragment_feedback_title.text = title
        photoBTN.text = photoBtnText
        reviewET.hint = reviewHint
        sendBTN.text = okBtnText
        view.dialog_fragment_feedback_button_cancel.text = closeBtnText

        val photoBTN = view.dialog_fragment_feedback_button_photo

        attachListeners(view.dialog_fragment_feedback_et_review)

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
        imageSelected = true
        fieldChanged()
    }

    override fun fieldChanged() {
        // Validation
        sendBTN.isEnabled = reviewET.text?.isNotEmpty() ?: false || imageSelected

        sendBTN.isEnabled = if (commentShouldBeFilled && imageShouldBeFilled) {
            imageSelected && Validator.commentFilled(reviewET.text)
        } else if (imageShouldBeFilled) {
            imageSelected
        } else if (commentShouldBeFilled) {
            Validator.commentFilled(reviewET.text)
        } else {
            false
        }
    }
}
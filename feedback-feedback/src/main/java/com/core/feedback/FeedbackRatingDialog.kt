package com.core.feedback

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import com.hanks.lineheightedittext.LineHeightEditText
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

    lateinit var reviewET: LineHeightEditText
    lateinit var ratingRB: RatingBar
    lateinit var sendBTN: Button

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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

        ratingRB.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, _, _ -> fieldChanged() }
        view.dialog_fragment_feedback_et_review.addTextWatcher(object : com.hanks.lineheightedittext.TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fieldChanged()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


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

    private fun fieldChanged() {
        // Validate Send Button
        sendBTN.isEnabled = ratingRB.rating > 0 && reviewET.text?.isNotEmpty() ?: false
    }
}
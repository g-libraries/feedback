package com.core.feedback.builder

import androidx.fragment.app.DialogFragment
import com.core.feedback.dialogs.FeedbackDialog
import com.core.feedback.dialogs.FeedbackRatingDialog
import com.core.feedback.dialogs.FeedbackRatingSmileDialog
import com.core.feedback.dialogs.FeedbackReviewDialog


class FeedbackDialogBuilder {

    inner class Selection(
        override var title: String?,
        override var closeBtnText: String?,
        var ratingBtnText: String?,
        var reviewBtnText: String?,
        var onItemClickAction: (pos: Int) -> Unit
        // You can also add some params for styling, now styling is performed in styles.xml
        // var backgroundDrawableId: Int? = null,
        // var selectionBtnDrawableId: Int? = null
    ) : Dialog() {
        // fun backgroundDrawableId(backgroundDrawableId: Int?) = apply { this.backgroundDrawableId = backgroundDrawableId }
        // fun selectionBtnDrawableId(selectionBtnStyleId: Int?) = apply { this.selectionBtnDrawableId = selectionBtnDrawableId }
        override fun build() = FeedbackDialog(
            title,
            ratingBtnText,
            reviewBtnText,
            onItemClickAction,
            closeBtnText
            // backgroundDrawableId,
            // selectionBtnDrawableId
        )
    }

    inner class Rating(
        override var title: String?,
        override var reviewHint: String?,
        override var ratingHint: String?,
        override var okBtnText: String?,
        override var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
        override var closeBtnText: String?
    ) : Dialog() {
        override fun build() = FeedbackRatingDialog(
            title,
            reviewHint,
            ratingHint,
            okBtnText,
            onOkBtnClickAction,
            closeBtnText
        )
    }

    inner class RatingSmiles(
        override var title: String?,
        override var reviewHint: String?,
        override var ratingHint: String?,
        override var okBtnText: String?,
        override var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
        override var closeBtnText: String?
    ) : Dialog() {
        override fun build() = FeedbackRatingSmileDialog(
            title,
            reviewHint,
            ratingHint,
            okBtnText,
            onOkBtnClickAction,
            closeBtnText
        )
    }

    inner class Review(
        override var title: String?,
        override var reviewHint: String?,
        override var photoBtnText: String?,
        override var onPhotoBtnClickAction: () -> Unit,
        override var okBtnText: String?,
        override var onOkBtnClickAction: (rating: Int, review: String) -> Unit,
        override var closeBtnText: String?
    ) : Dialog() {
        override fun build() = FeedbackReviewDialog(
            title,
            reviewHint,
            photoBtnText,
            onPhotoBtnClickAction,
            okBtnText,
            onOkBtnClickAction,
            closeBtnText
        )
    }

    abstract class Dialog(
        // General
        open var title: String? = null,
        open var reviewHint: String? = null,

        open var okBtnText: String? = null,
        open var closeBtnText: String? = null,

        open var onOkBtnClickAction: (rating: Int, review: String) -> Unit = { _, _ -> },

        // Review
        open var photoBtnText: String? = null,
        open var onPhotoBtnClickAction: () -> Unit = { },

        // Rating
        open var ratingStyleId: Int = 0,
        open var ratingHint: String? = null
    ) {
        fun title(title: String?) = apply { this.title = title }
        fun reviewHint(reviewHint: String?) = apply { this.reviewHint = reviewHint }

        fun okBtnText(okBtnText: String?) = apply { this.okBtnText = okBtnText }
        fun closeBtnText(closeBtnText: String?) = apply { this.closeBtnText = closeBtnText }
        fun onOkBtnClickAction(onOkBtnClickAction: (rating: Int, review: String) -> Unit) =
            apply { this.onOkBtnClickAction = onOkBtnClickAction }

        fun photoBtnText(photoBtnText: String?) = apply { this.photoBtnText = photoBtnText }
        fun onPhotoBtnClickAction(onPhotoBtnClickAction: () -> Unit) =
            apply { this.onPhotoBtnClickAction = onPhotoBtnClickAction }

        fun ratingStyleId(ratingStyleId: Int) = apply { this.ratingStyleId = ratingStyleId }
        fun ratingHint(ratingHint: String?) = apply { this.ratingHint = ratingHint }

        abstract fun build(): DialogFragment
    }
}
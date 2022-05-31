package com.example.articlespetapp

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import androidx.annotation.ColorInt

fun createHighlightedText(text: String, highlights: List<String>, @ColorInt color: Int): Spannable {
    val spannableMessage: Spannable = SpannableString(text)
    for (highlight in highlights) {
        spannableMessage.setSpan(
            BackgroundColorSpan(color),
            text.indexOf(highlight),
            text.indexOf(highlight) + highlight.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableMessage
}

fun String.getHighlightedString(@ColorInt color: Int): SpannableString {
    return SpannableString(this).also { it.setSpan(BackgroundColorSpan(color),0, this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
}
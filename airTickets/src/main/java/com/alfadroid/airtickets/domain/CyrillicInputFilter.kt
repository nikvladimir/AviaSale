package com.alfadroid.airtickets.domain

import android.text.InputFilter
import android.text.Spanned


class CyrillicInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (source == null) return null
        val pattern = Regex("[а-яА-ЯёЁ]+")
        val builder = StringBuilder()
        for (i in start until end) {
            val char = source[i]
            if (char.toString().matches(pattern)) {
                builder.append(char)
            }
        }
        return if (builder.isEmpty()) "" else builder.toString()
    }
}
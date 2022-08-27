package com.bignerdranch.android.testtask.feature_login.presentation.login

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import java.io.Serializable

class TextFieldState(
    text: String = "",
    selection: TextRange = TextRange.Zero,
    composition: TextRange? = null,
    var isFocused: Boolean = false
) : Serializable {


    var text: String = ""
        set(newValue) {
            field = newValue
            this.value = TextFieldValue(newValue, selection, composition)
        }

    var selection: TextRange = TextRange.Zero
        set(newValue) {
            field = newValue
            this.value = TextFieldValue(text, newValue, composition)
        }

    var composition: TextRange? = null
        set(newValue) {
            field = newValue
            this.value = TextFieldValue(text, selection, newValue)
        }

    init {
        this.text = text
        this.selection = selection
        this.composition = composition
    }


    var value = TextFieldValue(text, selection, composition)
}
package com.example.ui.utils

import android.content.Context
import com.example.ui.R
import com.example.viewmodel.StringsProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringsProviderImpl @Inject constructor(@ApplicationContext private val context: Context) :
    StringsProvider {
    override val userNameLessThanThree: String
        get() = getString(R.string.lessThanThreeUserName)
    override val passwordAndConfirmationNotTheSame: String
        get() = getString(R.string.passwordsAreNotTheSsame)
    override val emptyFields: String
        get() = getString(R.string.emptyFields)
    override val lessThanSixPassword: String
        get() = getString(R.string.passwordAtLeastSixChars)
    override val phoneNumberNotEgyptian: String
        get() = getString(R.string.egyptianPhoneNumbersOnly)
    override val phoneNumberLessThan11: String
        get() = getString(R.string.phoneNUmberLessThan11)
    override val invalidEmail: String
        get() = getString(R.string.invalidEmail)
    override val accountCreated: String
        get() = getString(R.string.accountCreated)

    private fun getString(stringsRes: Int): String {
        return context.getString(stringsRes)
    }
}
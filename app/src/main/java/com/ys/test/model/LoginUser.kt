package com.ys.test.model

import android.content.Context
import android.util.Patterns
import androidx.databinding.BaseObservable
import com.ys.test.R


class LoginUser(EmailAddress: String?, Password: String?) : BaseObservable() {
    private var userEmailAddress: String? = EmailAddress
    private var userPassword: String? = Password


    fun getUserEmailAddress(): String? {
        return userEmailAddress
    }

    fun getUserPassword(): String? {
        return userPassword
    }


    fun getUserLoginStatus(context: Context): String {
        return if (getUserEmailAddress().isNullOrEmpty()) {
            context.getString(R.string.empty_email_error_message)
        } else if (getUserPassword().isNullOrEmpty()) {
            context.getString(R.string.empty_password_error_Message)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getUserEmailAddress()!!).matches()) {
            context.getString(R.string.empty_password_error_Message)
        } else if (getUserPassword()!!.length > 5) {
            context.getString(R.string.empty_password_error_Message)
        } else {
            context.getString(R.string.success_message)
        }
        //return Patterns.EMAIL_ADDRESS.matcher(getUserEmailAddress()).matches()
    }

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(getUserEmailAddress()).matches()
    }


    fun isPasswordLengthGreaterThan5(): Boolean {
        return getUserPassword()!!.length > 5
    }

}
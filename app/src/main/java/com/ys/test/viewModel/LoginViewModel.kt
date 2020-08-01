package com.ys.test.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ys.test.model.LoginUser

class LoginViewModel : ViewModel() {
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var userMutableLiveData: MutableLiveData<LoginUser>? = null

    fun getUser(): MutableLiveData<LoginUser>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData<LoginUser>()
        }
        return userMutableLiveData
    }

    fun onClick(view: View?) {
        val loginUser = LoginUser(emailAddress.value, password.value)
        userMutableLiveData!!.value = loginUser
    }

}
package com.ys.test.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ys.test.R
import com.ys.test.databinding.ActivityLoginBinding
import com.ys.test.model.LoginUser
import com.ys.test.viewModel.LoginViewModel
import java.util.*


class LoginActivity : AppCompatActivity() {

    private var loginViewModel: LoginViewModel? = null
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)

        binding.lifecycleOwner = this

        binding.loginViewModel = loginViewModel
        binding.executePendingBindings()

        loginViewModel!!.getUser()!!.observe(this,
            Observer<LoginUser?> { loginUser ->
                var loginStatus = loginUser!!.getUserLoginStatus(this@LoginActivity)
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getUserEmailAddress())) {
                    binding.txtEmailAddress.error = "Enter an E-Mail Address"
                    binding.txtEmailAddress.requestFocus()
                } else if (!loginUser.isEmailValid()) {
                    binding.txtEmailAddress.error = "Enter a Valid E-mail Address"
                    binding.txtEmailAddress.requestFocus()
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getUserPassword())) {
                    binding.txtPassword.error = "Enter a Password"
                    binding.txtPassword.requestFocus()
                } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    binding.txtPassword.error = "Enter at least 6 Digit password"
                    binding.txtPassword.requestFocus()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.success_message),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

}
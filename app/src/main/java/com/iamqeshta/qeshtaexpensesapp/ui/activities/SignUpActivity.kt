package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivitySignUpBinding
import com.iamqeshta.qeshtaexpensesapp.models.User
import com.iamqeshta.qeshtaexpensesapp.roomdb.database.DatabaseClient

class SignUpActivity : LocalizationActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        checkLoginUIDarkMode()

        binding.signUpBtn.setOnClickListener { signUp() }
    }

    private fun signUp() {
        val name = binding.nameEdt.text.toString()
        val mobile = binding.mobileNumberEdt.text.toString()
        val email = binding.emailEdt.text.toString()
        val password = binding.passwordEdt.text.toString()
        val cPassword = binding.confirmPasswordEdt.text.toString()

        when {
            name.isEmpty() -> binding.nameEdt.error = getString(R.string.required)
            mobile.isEmpty() -> binding.mobileNumberEdt.error = getString(R.string.required)
            email.isEmpty() -> binding.emailEdt.error = getString(R.string.required)
            password.isEmpty() -> binding.passwordEdt.error = getString(R.string.required)
            password != cPassword -> binding.confirmPasswordEdt.error =
                getString(R.string.not_match)
            else -> {
                val user = User()
                user.uName = name
                user.uMobile = mobile
                user.uEmail = email
                user.uPassword = password
                DatabaseClient.getInstance(this)!!.appDatabase.userDao().insertUser(user)
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                intent.putExtra("EMAIL", email)
                intent.putExtra("PASSWORD", password)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun checkLoginUIDarkMode() {
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("LOGIN", "Two")
        edit.apply()
    }
}
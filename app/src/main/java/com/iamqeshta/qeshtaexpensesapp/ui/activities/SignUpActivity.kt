package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.CharSequenceTransformation
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivitySignUpBinding

class SignUpActivity : LocalizationActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        checkLoginUIDarkMode()

        binding.signUpBtn.setOnClickListener {
            signUp()
            //startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }
    }

    private fun signUp(){
        /*if(binding.nameEdt.text.toString().isEmpty())
            binding.nameEdt.error = */

        val name = binding.nameEdt.text.toString()
        val mobile = binding.mobileNumberEdt.text.toString()
        val email = binding.emailEdt.text.toString()
        val password = binding.passwordEdt.text.toString()
        val cPassword = binding.confirmPasswordEdt.text.toString()
    }

    private fun checkLoginUIDarkMode() {
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("LOGIN", "Two")
        edit.apply()
    }
}
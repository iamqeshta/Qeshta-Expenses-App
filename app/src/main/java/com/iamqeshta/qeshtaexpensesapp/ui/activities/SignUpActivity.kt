package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
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
            startActivity(Intent(this@SignUpActivity, MyExpensesActivity::class.java))
        }
    }

    private fun checkLoginUIDarkMode() {
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("LOGIN", "Two")
        edit.apply()
    }
}
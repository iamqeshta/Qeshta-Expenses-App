package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivityLoginBinding
import java.util.*

class LoginActivity : LocalizationActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        refreshUIForDarkMode()

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MyExpensesActivity::class.java))
        }

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun refreshUIForDarkMode(){
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        if (sharedPreferences.getString("LOGIN", "One") == "One" && sharedPreferences.getString("THEME", null) == "Dark"){
            val edit = sharedPreferences.edit()
            edit.putString("LOGIN", "Two")
            edit.apply()
            this.recreate()
        }
    }
}
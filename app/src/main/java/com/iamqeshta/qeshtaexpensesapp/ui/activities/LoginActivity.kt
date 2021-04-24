package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.R
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
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        refreshUIForDarkMode()
        checkLogin()

        binding.loginBtn.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            login()
            /*Handler().postDelayed(Runnable {
                login()
            }, 1000)*/

        }

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }
    
    private fun login(){
        if(binding.emailEdt.text.toString() == sharedPreferences.getString("EMAIL", null) && binding.passwordEdt.text.toString() == sharedPreferences.getString("PASSWORD", null)){
            binding.progress.visibility = View.GONE
            if(binding.rememberPassword.isChecked){
                val edit = sharedPreferences.edit()
                edit.putString("EMAIL", binding.emailEdt.text.toString())
                edit.putString("PASSWORD", binding.passwordEdt.text.toString())
                edit.apply()
            }
            startActivity(Intent(this@LoginActivity, MyExpensesActivity::class.java))
        } else{
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }
        
    }

    private fun checkLogin(){
        if(sharedPreferences.getString("EMAIL", null) != null && sharedPreferences.getString("PASSWORD", null) != null){
            startActivity(Intent(this@LoginActivity, MyExpensesActivity::class.java))
            finish()
        }
    }

    private fun refreshUIForDarkMode() {
        if (sharedPreferences.getString(
                "LOGIN",
                "One"
            ) == "One" && sharedPreferences.getString("THEME", null) == "Dark"
        ) {
            val edit = sharedPreferences.edit()
            edit.putString("LOGIN", "Two")
            edit.apply()
            this.recreate()
        }
    }
}
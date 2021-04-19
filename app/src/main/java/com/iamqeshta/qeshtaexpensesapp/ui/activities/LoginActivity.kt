package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivityLoginBinding

class LoginActivity : LocalizationActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MyExpensesActivity::class.java))
        }

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }
}
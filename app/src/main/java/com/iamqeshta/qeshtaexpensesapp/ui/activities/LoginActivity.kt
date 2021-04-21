package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivityLoginBinding

class LoginActivity : LocalizationActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        checkTheme()

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MyExpensesActivity::class.java))
        }

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun checkTheme() {
        if (sharedPreferences.getString("THEME", null) == "Light")
            light()
        else if (sharedPreferences.getString("THEME", null) == "Dark")
            dark()
    }

    private fun light() {
        binding.logo.background.setTint(resources.getColor(R.color.primary_light, null))
        binding.appName.setTextColor(resources.getColor(R.color.primary_text_light, null))
        binding.appSlogan.setTextColor(resources.getColor(R.color.secondary_text_icon_light, null))
    }

    private fun dark() {
        binding.logo.background.setTint(resources.getColor(R.color.system_bar_light_primary_dark, null))
        binding.appName.setTextColor(resources.getColor(R.color.primary_text_icon_dark, null))
        binding.appSlogan.setTextColor(resources.getColor(R.color.secondary_text_dark, null))
        //binding.emailEdt.highlightColor = resources.getColor(R.color.system_bar_light_primary_dark, null)
        //binding.email.boxStrokeColor = resources.getColor(R.color.system_bar_light_primary_dark, null)
        binding.emailEdt.background.setTint(resources.getColor(R.color.primary_text_icon_dark, null))

        binding.rememberPassword.setTextColor(resources.getColor(R.color.primary_text_icon_dark, null))
        binding.loginBtn.setTextColor(resources.getColor(R.color.primary_text_icon_dark, null))
        binding.loginBtn.setBackgroundColor(resources.getColor(R.color.background_dark, null))
        binding.signUpBtn.setBackgroundColor(resources.getColor(R.color.system_bar_light_primary_dark, null))
    }
}
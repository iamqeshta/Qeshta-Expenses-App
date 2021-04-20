package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivitySplashBinding


class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        checkTheme()
        openActivity()

    }

    private fun openActivity(){
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@Splash, LoginActivity::class.java))
        }, 1000)
    }

    private fun checkTheme() {
        if (sharedPreferences.getString("THEME", null) == "Light")
            light()
        else if (sharedPreferences.getString("THEME", null) == "Dark")
            dark()

    }

    private fun light(){
        window.statusBarColor = ContextCompat.getColor(this, R.color.system_bar_light_primary_dark)
        binding.root.setBackgroundColor(resources.getColor(R.color.background_light, null))
        binding.logo.background.setTint(resources.getColor(R.color.primary_light, null))
        binding.appName.setTextColor(resources.getColor(R.color.primary_text_light, null))
        binding.appSlogan.setTextColor(resources.getColor(R.color.secondary_text_icon_light, null))
    }

    private fun dark(){
        window.statusBarColor = ContextCompat.getColor(this, R.color.system_bar_dark)
        binding.root.setBackgroundColor(resources.getColor(R.color.background_dark, null))
        binding.logo.background.setTint(resources.getColor(R.color.system_bar_light_primary_dark, null))
        binding.appName.setTextColor(resources.getColor(R.color.primary_text_icon_dark, null))
        binding.appSlogan.setTextColor(resources.getColor(R.color.secondary_text_dark, null))
    }
}

package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        checkTheme()

        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_logo)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.containerLogo.startAnimation(animation)
    }

    private fun checkTheme() {
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        if (sharedPreferences.getString("THEME", null) == "Light") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            light()
        } else if (sharedPreferences.getString("THEME", null) == "Dark") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            dark()
            checkLoginUIDarkMode()
        }
    }

    private fun light() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.system_bar_light_primary_dark)
        binding.root.setBackgroundColor(resources.getColor(R.color.background_light, null))
        binding.logo.background.setTint(resources.getColor(R.color.primary_light, null))
        binding.appName.setTextColor(resources.getColor(R.color.primary_text_light, null))
        binding.appSlogan.setTextColor(resources.getColor(R.color.secondary_text_icon_light, null))
    }

    private fun dark() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.system_bar_dark)
        binding.root.setBackgroundColor(resources.getColor(R.color.background_dark, null))
        binding.logo.background.setTint(
            resources.getColor(
                R.color.system_bar_light_primary_dark,
                null
            )
        )
        binding.appName.setTextColor(resources.getColor(R.color.primary_text_icon_dark, null))
        binding.appSlogan.setTextColor(resources.getColor(R.color.secondary_text_dark, null))
    }

    private fun checkLoginUIDarkMode() {
        val edit = sharedPreferences.edit()
        edit.putString("LOGIN", "One")
        edit.apply()
    }
}

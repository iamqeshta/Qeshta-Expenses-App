package com.iamqeshta.qeshtaexpensesroomdb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.iamqeshta.qeshtaexpensesroomdb.databinding.ActivitySplashBinding


class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        openActivity()

    }

    private fun openActivity(){
        Handler().postDelayed(Runnable {
            val i = Intent(this@Splash, LoginActivity::class.java)
            startActivity(i)
        }, 3000)
    }
}

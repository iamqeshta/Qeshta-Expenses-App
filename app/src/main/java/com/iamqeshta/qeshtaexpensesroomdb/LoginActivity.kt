package com.iamqeshta.qeshtaexpensesroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iamqeshta.qeshtaexpensesroomdb.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}
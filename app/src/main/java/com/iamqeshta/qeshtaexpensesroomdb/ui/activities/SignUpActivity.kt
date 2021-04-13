package com.iamqeshta.qeshtaexpensesroomdb.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iamqeshta.qeshtaexpensesroomdb.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}
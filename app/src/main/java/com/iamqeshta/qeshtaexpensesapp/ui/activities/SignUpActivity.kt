package com.iamqeshta.qeshtaexpensesapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivitySignUpBinding

class SignUpActivity : LocalizationActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}
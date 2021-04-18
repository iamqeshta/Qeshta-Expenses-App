package com.iamqeshta.qeshtaexpensesapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivitySettingsBinding
import com.iamqeshta.qeshtaexpensesapp.ui.fragments.ProfileFragment
import com.iamqeshta.qeshtaexpensesapp.ui.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.appBar.searchIcon.visibility = View.INVISIBLE
        binding.appBar.menuIcon.setImageResource(R.drawable.ic_back)
        binding.appBar.menuIcon.setOnClickListener {
            finish()
        }

        switchFragment(SettingsFragment())
        binding.appBar.pageTitle.text = getString(R.string.settings)
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_setting -> {
                    switchFragment(SettingsFragment())
                    binding.appBar.pageTitle.text = getString(R.string.settings)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    switchFragment(ProfileFragment())
                    binding.appBar.pageTitle.text = getString(R.string.profile)
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
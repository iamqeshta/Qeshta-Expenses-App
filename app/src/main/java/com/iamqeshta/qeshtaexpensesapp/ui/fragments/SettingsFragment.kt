package com.iamqeshta.qeshtaexpensesapp.ui.fragments

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.FragmentSettingsBinding
import com.iamqeshta.qeshtaexpensesapp.ui.activities.SettingsActivity
import com.iamqeshta.qeshtaexpensesapp.ui.activities.SignUpActivity
import com.iamqeshta.qeshtaexpensesapp.ui.activities.Splash
import java.util.*

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        sharedPreferences =
            activity!!.getSharedPreferences("SETTINGS_APP", AppCompatActivity.MODE_PRIVATE)

        checkLanguage()
        binding.englishRb.setOnClickListener { changeLanguage("en") }
        binding.arabicRb.setOnClickListener { changeLanguage("ar") }

        return binding.root
    }

    private fun changeLanguage(lang: String) {
        setLanguage(activity!!, Locale(lang))
        val edit = sharedPreferences.edit()
        edit.putString("LANGUAGE", lang)
        edit.apply()
        startActivity(Intent(activity!!, Splash::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ))
        //activity!!.finish()

    }

    private fun checkLanguage() {
        if (sharedPreferences.getString("LANGUAGE", null) == "en")
            binding.englishRb.isChecked = true
        else if (sharedPreferences.getString("LANGUAGE", null) == "ar")
            binding.arabicRb.isChecked = true
    }

    private fun triggerRestart(context: Activity) {
        val intent = Intent(context, Splash::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        context.finish()
        Runtime.getRuntime().exit(0)
    }

    private fun dialog() {
        val dialog = AlertDialog.Builder(activity!!)
        dialog.setTitle("Change Language : Restart Application")
        dialog.setMessage("Are you sure you want to exit?")
        dialog.setIcon(R.drawable.ic_back)
        dialog.setPositiveButton("Yes") { p0, p1 ->

        }
        dialog.setNegativeButton("No") { p0, p1 -> }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
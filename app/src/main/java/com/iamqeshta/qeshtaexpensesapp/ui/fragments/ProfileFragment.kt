package com.iamqeshta.qeshtaexpensesapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.FragmentProfileBinding
import com.iamqeshta.qeshtaexpensesapp.models.User
import com.iamqeshta.qeshtaexpensesapp.roomdb.database.DatabaseClient

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferences =
            activity!!.getSharedPreferences("SETTINGS_APP", AppCompatActivity.MODE_PRIVATE)
        getUser()
        binding.saveBtn.setOnClickListener { updateUser() }
        return binding.root
    }

    private fun updateUser() {
        val name = binding.nameEdt.text.toString()
        val mobile = binding.mobileNumberEdt.text.toString()
        val email = binding.emailEdt.text.toString()
        val password = binding.passwordEdt.text.toString()
        val cPassword = binding.confirmPasswordEdt.text.toString()
        when {
            name.isEmpty() -> binding.nameEdt.error = getString(R.string.required)
            mobile.isEmpty() -> binding.mobileNumberEdt.error = getString(R.string.required)
            email.isEmpty() -> binding.emailEdt.error = getString(R.string.required)
            password.isEmpty() -> binding.passwordEdt.error = getString(R.string.required)
            password != cPassword -> binding.confirmPasswordEdt.error =
                getString(R.string.not_match)
            else -> {
                this.user?.uName = name
                this.user?.uMobile = mobile
                this.user?.uEmail = email
                this.user?.uPassword = password
                DatabaseClient.getInstance(context)!!.appDatabase.userDao().updateUser(user!!)
                Toast.makeText(context, R.string.saved_successfully, Toast.LENGTH_SHORT).show()
                val edit = sharedPreferences.edit()
                edit.putString("EMAIL", email)
                edit.putString("PASSWORD", password)
                edit.apply()
            }
        }
    }

    private fun getUser() {
        this.user = DatabaseClient.getInstance(context)!!.appDatabase.userDao()
            .getUser(
                sharedPreferences.getString("EMAIL", null)!!,
                sharedPreferences.getString("PASSWORD", null)!!
            )
        if (user != null) {
            binding.nameEdt.setText(user!!.uName)
            binding.mobileNumberEdt.setText(user!!.uMobile)
            binding.emailEdt.setText(user!!.uEmail)
            binding.passwordEdt.setText(user!!.uPassword)
            binding.confirmPasswordEdt.setText(user!!.uPassword)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
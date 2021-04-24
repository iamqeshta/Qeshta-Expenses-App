package com.iamqeshta.qeshtaexpensesapp.ui.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.FragmentSettingsBinding
import com.iamqeshta.qeshtaexpensesapp.services.AlarmBroadcastReceiver
import com.iamqeshta.qeshtaexpensesapp.ui.activities.SplashActivity
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

        checkTheme()
        binding.themeSw.setOnClickListener {
            if (binding.themeSw.isChecked) changeTheme("Dark")
            else changeTheme("Light")
        }

        checkNotifications()
        binding.notificationsOnBtn.setOnClickListener { turnOnOffNotifications("On") }
        binding.notificationsOffBtn.setOnClickListener { turnOnOffNotifications("Off") }

        binding.privacyTv.setOnClickListener {
            Toast.makeText(activity, "Privacy", Toast.LENGTH_SHORT).show()
        }

        binding.logoutTv.setOnClickListener { confirmDialogForLogout() }

        return binding.root
    }

    private fun checkLanguage() {
        if (sharedPreferences.getString("LANGUAGE", null) == "en") {
            binding.englishRb.isChecked = true
            binding.arabicRb.buttonTintList = ColorStateList.valueOf(
                resources.getColor(
                    R.color.disabled_text_icon_dark,
                    null
                )
            )
        } else if (sharedPreferences.getString("LANGUAGE", null) == "ar") {
            binding.arabicRb.isChecked = true
            binding.englishRb.buttonTintList = ColorStateList.valueOf(
                resources.getColor(
                    R.color.disabled_text_icon_dark,
                    null
                )
            )
        }
    }

    private fun changeLanguage(lang: String) {
        setLanguage(activity!!, Locale(lang))
        val edit = sharedPreferences.edit()
        edit.putString("LANGUAGE", lang)
        edit.apply()
        restartApp()
    }

    private fun changeTheme(theme: String) {
        val edit = sharedPreferences.edit()
        edit.putString("THEME", theme)
        edit.apply()
        restartApp()
    }

    private fun checkTheme() {
        if (sharedPreferences.getString("THEME", null) == "Light")
            binding.themeSw.isChecked = false
        else if (sharedPreferences.getString("THEME", null) == "Dark")
            binding.themeSw.isChecked = true

    }

    private fun checkNotifications() {
        if (sharedPreferences.getString("THEME", null) == "Light") {
            if (sharedPreferences.getString("NOTIFICATIONS", null) == "On") {
                binding.notificationsOnBtn.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.focus_action_light, null))
                binding.notificationsOffBtn.backgroundTintList =
                    ColorStateList.valueOf(
                        resources.getColor(
                            R.color.secondary_text_icon_light,
                            null
                        )
                    )
            } else if (sharedPreferences.getString("NOTIFICATIONS", null) == "Off") {
                binding.notificationsOffBtn.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.focus_action_light, null))
                binding.notificationsOnBtn.backgroundTintList =
                    ColorStateList.valueOf(
                        resources.getColor(
                            R.color.secondary_text_icon_light,
                            null
                        )
                    )
            }
        } else if (sharedPreferences.getString("THEME", null) == "Dark") {
            if (sharedPreferences.getString("NOTIFICATIONS", null) == "On") {
                binding.notificationsOnBtn.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.focus_action_dark, null))
                binding.notificationsOffBtn.backgroundTintList =
                    ColorStateList.valueOf(
                        resources.getColor(
                            R.color.disabled_text_icon_dark,
                            null
                        )
                    )
            } else if (sharedPreferences.getString("NOTIFICATIONS", null) == "Off") {
                binding.notificationsOffBtn.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.focus_action_dark, null))
                binding.notificationsOnBtn.backgroundTintList =
                    ColorStateList.valueOf(
                        resources.getColor(
                            R.color.disabled_text_icon_dark,
                            null
                        )
                    )
            }
        }
    }

    private fun turnOnOffNotifications(action: String) {
        val edit = sharedPreferences.edit()
        edit.putString("NOTIFICATIONS", action)
        edit.apply()
        checkNotifications()
        if (action == "On") {
            Toast.makeText(activity, R.string.notifications_on, Toast.LENGTH_SHORT).show()
            animNotification(action)
            startAlarmBroadcastReceiver(context)
        } else if (action == "Off") {
            Toast.makeText(activity, R.string.notifications_off, Toast.LENGTH_SHORT).show()
            animNotification(action)
            stopAlarmBroadcastReceiver(context)
        }
    }

    private fun startAlarmBroadcastReceiver(context: Context?) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Objects.requireNonNull(alarmManager).cancel(pendingIntent)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun stopAlarmBroadcastReceiver(context: Context?) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Objects.requireNonNull(alarmManager).cancel(pendingIntent)
    }

    private fun animNotification(action: String) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_notification)
        AnimationUtils.loadAnimation(context, R.anim.anim_notification)
            .setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
            })
        if (action == "On") binding.notificationsOnBtn.startAnimation(animation)
        else if (action == "Off") binding.notificationsOffBtn.startAnimation(animation)
    }

    private fun confirmDialogForLogout() {
        val dialog = AlertDialog.Builder(activity!!)
        dialog.setTitle(resources.getString(R.string.logout))
        dialog.setMessage(resources.getString(R.string.logout_message))
        dialog.setIcon(R.drawable.ic_logo)
        dialog.setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
            val edit = sharedPreferences.edit()
            edit.putString("EMAIL", null)
            edit.putString("PASSWORD", null)
            edit.apply()
            activity!!.finish()
            restartApp()
        }
        dialog.setNegativeButton(resources.getString(R.string.no)) { _, _ -> }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }

    private fun restartApp() {
        startActivity(
            Intent(
                activity!!,
                SplashActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
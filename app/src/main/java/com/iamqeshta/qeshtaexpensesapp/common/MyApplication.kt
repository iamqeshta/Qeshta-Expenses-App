package com.iamqeshta.qeshtaexpensesapp.common

import com.akexorcist.localizationactivity.ui.LocalizationApplication
import java.util.*

class MyApplication : LocalizationApplication() {
    override fun getDefaultLanguage(): Locale {
        return Locale.ENGLISH
    }
}
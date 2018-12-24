package co.winds.myapplication.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import java.util.*


object LocaleHelper {

    fun onAttach(context: Context): Context {
        val lang = SharedPrefUtils.instance.checkLanguage
        return setLocale(context, lang!!)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun setLocale(context: Context, language: String): Context {
        SharedPrefUtils.instance.checkLanguage = language
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

}

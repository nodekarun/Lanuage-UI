package co.winds.myapplication.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import co.winds.myapplication.R
import co.winds.myapplication.language.LanguageActivity
import co.winds.myapplication.utils.SharedPrefUtils
import co.winds.myapplication.utils.startNewActivity

class SplashActivity : AppCompatActivity() {

    private val sp  by lazy { SharedPrefUtils.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            val lang = sp.checkLanguage
            if (lang == null || TextUtils.isEmpty(lang)) {
                startNewActivity(LanguageActivity::class.java)
            } else {
                startNewActivity(HomeActivity::class.java)
            }
        },1000)
    }
}

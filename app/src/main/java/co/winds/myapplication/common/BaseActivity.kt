package co.winds.myapplication.common


import android.content.Context
import android.support.v7.app.AppCompatActivity
import co.winds.myapplication.utils.LocaleHelper


abstract class BaseActivity : AppCompatActivity(){


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }



}

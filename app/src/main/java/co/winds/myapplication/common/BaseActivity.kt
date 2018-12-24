package com.winds.karyakarta.common


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.winds.karyakarta.R
import com.winds.karyakarta.utils.LocaleHelper
import com.winds.karyakarta.utils.Utils


abstract class BaseActivity : AppCompatActivity(){

   // var firebaseAnalytics: FirebaseAnalytics? = null
    //    private set




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.leftToRight(this)


        try {
           // https://medium.com/@iiro.krankka/its-time-to-kiss-goodbye-to-your-implicit-broadcastreceivers-eefafd9f4f8a
//            registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }catch (e:Exception){}

       // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
       // firebaseAnalytics = FirebaseAnalytics.getInstance(this)

    }




    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Utils.rightToLeft(this)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.rightToLeft(this)
    }

    fun showSnackBar(str:String) {
        Utils.showSnackBar(this, str, Color.WHITE)
    }

    fun showSnackBarErr(){
        Utils.showSnackBarErr(this,getString(R.string.internet_disconnect), Color.RED)

    }



}

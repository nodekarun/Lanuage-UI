package com.winds.karyakarta

import android.app.Application
import android.content.Context
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig
import com.winds.karyakarta.network.Const.FRESH_CHAT_APP_ID
import com.winds.karyakarta.network.Const.FRESH_CHAT_APP_KEY
import com.google.firebase.FirebaseApp




class KaryaKartaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
         initilizeFreshChat()

       /* val fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(true)  // Enables Crashlytics debugger
                .build()
        Fabric.with(fabric)

*/




    }
    companion object {
        @get:Synchronized
        var appContext: Context? = null
            private set


    }


    private fun initilizeFreshChat() {
        try {
            val freshConfig = FreshchatConfig(FRESH_CHAT_APP_ID, FRESH_CHAT_APP_KEY)
            freshConfig.isCameraCaptureEnabled = true
            freshConfig.isGallerySelectionEnabled = true
            Freshchat.getInstance(appContext!!).init(freshConfig)
        } catch (e: Exception) {
        }
    }


}

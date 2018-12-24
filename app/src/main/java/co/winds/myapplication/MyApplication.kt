package co.winds.myapplication

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }
    companion object {
        @get:Synchronized
        var appContext: Context? = null
            private set

    }
}

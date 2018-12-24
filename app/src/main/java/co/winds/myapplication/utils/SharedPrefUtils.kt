package com.winds.karyakarta.utils

import android.content.Context
import com.winds.karyakarta.KaryaKartaApplication


class SharedPrefUtils private constructor() {

    companion object {
        val instance = SharedPrefUtils()


        private val preference_file_key = "com.demo.winds_preference"

        private val LANGUAGE = "language"
        private val IS_LOGGED_IN = "is_logged_in"
        private val IS_VIDEO_VIEW = "is_video"

        private val USER_ID = "user_id"
        private val USER_PASSWORD = "user_password"
        private val USER_NAME = "user_name"
        private val GENDER = "gender"
        private val MOBILE_NUMBER = "mobile_number"
        private val EMAIL_ID = "email_id"
        private val PROFILE_AVATAR = "profile_picture_url"
        private val AUTH_TOKEN = "auth_token"
        private val RefreshToken_TOKEN = "refreshToken"
    }


    private val sharedPref = KaryaKartaApplication.appContext!!.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()



    var isLoginStatus:Int
        get() = sharedPref.getInt(IS_LOGGED_IN, 0)
        set(isLoginStatus) {
            editor.putInt(IS_LOGGED_IN, isLoginStatus)
            editor.apply()
        }


    var userId: String?
        get() = sharedPref.getString(USER_ID, "")
        set(userId) {
            editor.putString(USER_ID, userId)
            editor.apply()
        }

    var userName: String?
        get() = sharedPref.getString(USER_NAME, "")
        set(userName) {
            editor.putString(USER_NAME, userName)
            editor.apply()
        }

    var checkLanguage: String?
        get() = sharedPref.getString(LANGUAGE, "")
        set(language) {
            editor.putString(LANGUAGE, language)
            editor.apply()
        }

    var mobileNumber: String?
        get() = sharedPref.getString(MOBILE_NUMBER, "")
        set(mobile) {
            editor.putString(MOBILE_NUMBER, mobile)
            editor.apply()
        }

    var emailId: String?
        get() = sharedPref.getString(EMAIL_ID, "")
        set(emailId) {
            editor.putString(EMAIL_ID, emailId)
            editor.apply()
        }

    var gender: Int
        get() = sharedPref.getInt(GENDER, 0)
        set(gender) {
            editor.putInt(GENDER, gender)
            editor.apply()
        }

    var profileAvatar: String?
        get() = sharedPref.getString(PROFILE_AVATAR, "")
        set(avatar) {
            editor.putString(PROFILE_AVATAR, avatar)
            editor.apply()
        }

    var authToken: String?
        get() = sharedPref.getString(AUTH_TOKEN, "")
        set(authToken) {
            editor.putString(AUTH_TOKEN, authToken)
            editor.apply()
        }

 var refreshToken: String?
        get() = sharedPref.getString(RefreshToken_TOKEN, "")
        set(refreshToken) {
            editor.putString(RefreshToken_TOKEN, refreshToken)
            editor.apply()
        }


    var isVideoView: Boolean
        get() = sharedPref.getBoolean(IS_VIDEO_VIEW, false)
        set(isVideoView) {
            editor.putBoolean(IS_VIDEO_VIEW, isVideoView)
            editor.apply()
        }

    fun logOut() {
        val lang = SharedPrefUtils.instance.checkLanguage
        editor.clear().apply()
        SharedPrefUtils.instance.checkLanguage=lang!!
        SharedPrefUtils.instance.isVideoView=true

    }
}

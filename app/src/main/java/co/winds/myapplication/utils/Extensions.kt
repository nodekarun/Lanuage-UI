package co.winds.myapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast






/*New Activity Start*/
fun AppCompatActivity.startNewActivity( cls : Class<*>){
    this.startActivity(Intent(this,cls))
    this.finish()
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun Context.log(message: String) {
    Log.d("TAGS",message)
}

fun Context.hideSoftKeyboard() {
    try {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((this as Activity).currentFocus!!.windowToken, 0)
    } catch (e: Exception) {
    }

}
//Custom Snackbar
fun Context.showSnackBarErr(): Snackbar {
    val s="internet_disconnect"
    val sb = Snackbar.make((this as Activity).findViewById<View>(android.R.id.content), s, Snackbar.LENGTH_LONG)
    sb.view.setBackgroundColor(Color.WHITE)
    val textView = sb.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
    textView.setTextColor(Color.RED)
    sb.show()
    return sb
}


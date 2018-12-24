package com.winds.karyakarta.utils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.location.Geocoder
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.model.BitmapDescriptor
import com.winds.karyakarta.R


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
    val s=getString(R.string.internet_disconnect)
    val sb = Snackbar.make((this as Activity).findViewById<View>(android.R.id.content), s, Snackbar.LENGTH_LONG)
    sb.view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
    val textView = sb.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
    textView.setTextColor(Color.RED)
    sb.show()
    return sb
}


fun Context.getAddress(location: LatLng): String {
    val geocoder = Geocoder(this, Locale.getDefault())
    var addresstxt = ""
    try {
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (null != addresses && !addresses.isEmpty()) {
            addresstxt = "" + addresses[0].getAddressLine(0)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        addresstxt=""
    }
    return addresstxt
}

 fun Context.bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap =
        Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

fun Context.drawTextToBitmap(gResId: Int, gText: String): Bitmap {
    val resources = resources
    val scale = resources.displayMetrics.density
    var bitmap = BitmapFactory.decodeResource(resources, gResId)
    var bitmapConfig: android.graphics.Bitmap.Config? = bitmap.config

    if (bitmapConfig == null) {
        bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888
    }
    bitmap = bitmap.copy(bitmapConfig, true)
    val canvas = Canvas(bitmap)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /* SET FONT COLOR (e.g. WHITE -> rgb(255,255,255)) */
    paint.color = Color.rgb(255, 255, 255)
    /* SET FONT SIZE (e.g. 15) */
    paint.textSize = (10 * scale).toInt().toFloat()
    /* SET SHADOW WIDTH, POSITION AND COLOR (e.g. BLACK) */
    paint.setShadowLayer(0f, 0f, 4f, Color.BLACK)

    val bounds = Rect()
    paint.getTextBounds(gText, 0, gText.length, bounds)
    val x = (bitmap.width - bounds.width()) / 2
    val y = (bitmap.height + bounds.height()) / 2.5
    canvas.drawText(gText, x.toFloat(), y.toFloat(), paint)


    return getResizedBitmap(bitmap,110,140)
}

fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
    val width = bm.width
    val height = bm.height
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height
    // CREATE A MATRIX FOR THE MANIPULATION
    val matrix = Matrix()
    // RESIZE THE BIT MAP
    matrix.postScale(scaleWidth, scaleHeight)

    // "RECREATE" THE NEW BITMAP
    val resizedBitmap = Bitmap.createBitmap(
            bm, 0, 0, width, height, matrix, false
    )
    bm.recycle()
    return resizedBitmap
}

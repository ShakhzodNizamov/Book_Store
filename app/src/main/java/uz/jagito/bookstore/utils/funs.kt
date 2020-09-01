package uz.jagito.bookstore.utils

import android.content.Context
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import uz.jagito.bookstore.R
import java.text.SimpleDateFormat
import java.util.*

fun getRoundedCornerBitmap(bitmap: Bitmap, pixels: Int): Bitmap {
    val output = Bitmap.createBitmap(
        bitmap.width, bitmap
            .height, Config.ARGB_8888
    )
    val canvas = Canvas(output)
    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)
    val rectF = RectF(rect)
    val roundPx = pixels.toFloat()
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
    paint.xfermode = PorterDuffXfermode(Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)
    return output
}

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    }
}

fun showToast(message: String){
    Toast.makeText(APP_ACTIVITY,message, Toast.LENGTH_SHORT).show()
}

fun enableToolbar() {
    APP_ACTIVITY.mToolbar.visible()
    APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
        hideKeyboard()
        APP_ACTIVITY.supportFragmentManager.popBackStack()
    }
}

fun disableToolbar() {
    APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    APP_ACTIVITY.mToolbar.gone()
}

fun hideKeyboard() {
    val imm: InputMethodManager =
        APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}

fun ImageView.downloadAndSetImage(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .into(this)
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}
fun View.isVisible(): Boolean = visibility == View.VISIBLE
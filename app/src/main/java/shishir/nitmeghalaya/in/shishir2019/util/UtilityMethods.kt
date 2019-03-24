package shishir.nitmeghalaya.`in`.shishir2019.util

import android.content.Context
import android.widget.Toast
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.palette.graphics.Palette
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent

/**
 * Created by Devansh on 3/3/19.
 */

public fun makeShortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun drawableToBitmap(drawable: Drawable): Bitmap {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }

    var width = drawable.intrinsicWidth
    width = if (width > 0) width else 1
    var height = drawable.intrinsicHeight
    height = if (height > 0) height else 1

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}

fun getImageResource(context: Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}

fun createForegroundGradient(context: Context, imageResId: Int): GradientDrawable {

    val color = getDominantImageColor(context, imageResId)

    val gradientColorsArray: IntArray = intArrayOf(
        getColorWithAddedAlpha(color, 0xff), Color.TRANSPARENT, Color.TRANSPARENT)

    return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, gradientColorsArray)
}

fun getDominantImageColor(context: Context, imageResId: Int): Int {
    val eventImageBitmap = BitmapFactory.decodeResource(context.resources, imageResId)
    val palette = Palette.from(eventImageBitmap).generate()
    return palette.getDominantColor(Color.BLACK)
}

fun getColorWithAddedAlpha(color: Int, alpha: Int): Int {

    val red = (color ushr 16) and 0xff
    val green = (color ushr 8) and 0xff
    val blue = color and 0xff

    return Color.argb(alpha, red, green, blue)
}

fun getTitleTextColorForImage(context: Context, imageResId: Int): Int {
    val eventImageBitmap = BitmapFactory.decodeResource(context.resources, imageResId)
    val palette = Palette.from(eventImageBitmap).generate()
    val color = palette.dominantSwatch?.titleTextColor ?: Color.WHITE
    return stripAlpha(color)
}

fun getBodyTextColorForImage(context: Context, imageResId: Int): Int {
    val eventImageBitmap = BitmapFactory.decodeResource(context.resources, imageResId)
    val palette = Palette.from(eventImageBitmap).generate()
    val color = palette.dominantSwatch?.bodyTextColor ?: Color.WHITE
    return stripAlpha(color)
}

fun stripAlpha(color: Int): Int {
    return Color.rgb(Color.red(color), Color.green(color), Color.blue(color))
}

public inline fun <reified T> Gson.getListFromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

public inline fun <reified T> Gson.getJsonFromList(eventsList: ArrayList<*>) =
    this.toJson(eventsList, object : TypeToken<T>() {}.type)
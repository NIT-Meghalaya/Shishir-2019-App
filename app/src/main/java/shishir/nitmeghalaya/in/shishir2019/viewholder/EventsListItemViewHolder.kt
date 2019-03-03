package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_item.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.activity.EventDetailActivity
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import android.graphics.BitmapFactory
import android.graphics.Color
import com.bumptech.glide.request.RequestOptions
import shishir.nitmeghalaya.`in`.shishir2019.util.getImageResource

/**
 * Created by Devansh on 24/1/19.
 */

class EventsListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val context = view.context
    private lateinit var item: ShishirEvent
    private var imageResId = 0

    fun bind(item: ShishirEvent) {
        this.item = item

        if (item.image.isEmpty())
            item.image = "krigg"

        imageResId = getImageResource(context, item.image)

        view.apply {
            eventListItemTitle.text = item.name

            val options = RequestOptions().placeholder(R.drawable.krigg)

            Glide.with(this).load(getImageResource(context, item.image)).apply(options).into(eventListItemImage)

            setOnClickListener {
                val intent = Intent(view.context, EventDetailActivity::class.java)
                view.context.startActivity(intent)
            }

//            if (android.os.Build.VERSION.SDK_INT >= 23)
//                eventListItemImage.foreground = createForegroundGradient()
        }
    }

    private fun createForegroundGradient(): GradientDrawable {


        val eventImageBitmap = BitmapFactory.decodeResource(context.resources, imageResId)

        val palette = Palette.from(eventImageBitmap).generate()

        var color: Int

        color = palette.getMutedColor(ContextCompat.getColor(context, R.color.black))
        if (color == Color.BLACK) {
            color = palette.getLightMutedColor(ContextCompat.getColor(context, R.color.black))
            if(color == Color.BLACK)
                color = palette.getVibrantColor(ContextCompat.getColor(context, R.color.black))
        }

        //color = palette.getVibrantColor(ContextCompat.getColor(context, R.color.black))

        val gradientColorsArray: IntArray = intArrayOf(
            getColorWithAddedAlpha(color, 0xff),
            Color.TRANSPARENT,
            Color.TRANSPARENT
        )

        return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, gradientColorsArray)
    }

    private fun getColorWithAddedAlpha(color: Int, alpha: Int): Int {

        val red = (color ushr 16) and 0xff
        val green = (color ushr 8) and 0xff
        val blue = color and 0xff

        return Color.argb(alpha, red, green, blue)
    }
}
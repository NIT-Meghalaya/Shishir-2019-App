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
import android.widget.ImageView

/**
 * Created by Devansh on 24/1/19.
 */

class EventsListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val context = view.context

    fun bind(item: ShishirEvent) {
        view.apply {
            eventListItemTitle.text = item.name
            Glide.with(this).load(R.drawable.samplebg).into(eventListItemImage)
            setOnClickListener {
                val intent = Intent(view.context, EventDetailActivity::class.java)
                view.context.startActivity(intent)
            }

//            if (android.os.Build.VERSION.SDK_INT >= 23)
//                foreground = createForegroundGradient()
        }
    }

    private fun createForegroundGradient(): GradientDrawable {
        val eventImageBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.samplebg)

        val palette = Palette.from(eventImageBitmap).generate()

        val gradientColorsArray: IntArray = intArrayOf(
            palette.getMutedColor(ContextCompat.getColor(context, R.color.black)),
            palette.getVibrantColor(ContextCompat.getColor(context, R.color.transparent)),
            ContextCompat.getColor(context, R.color.transparent)
        )

        return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, gradientColorsArray)
    }
}
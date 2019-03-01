package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_item.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.activity.EventDetailActivity
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard

/**
 * Created by Devansh on 24/1/19.
 */

class EventsListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: EventCard) {
        view.apply {
            eventListItemType.text = item.type
            eventListItemTitle.text = item.title
            Glide.with(this).load(R.drawable.samplebg).into(eventListItemImage)
            setOnClickListener {
                val intent = Intent(view.context, EventDetailActivity::class.java)
                view.context.startActivity(intent)
            }
        }
    }
}
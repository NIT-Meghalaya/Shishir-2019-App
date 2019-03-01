package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.event_item.view.*
import shishir.nitmeghalaya.`in`.shishir2019.activity.EventsDetailActivity
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard

/**
 * Created by Devansh on 24/1/19.
 */

class EventsListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: EventCard) {
        view.eventListItemType.text = item.type
        view.eventListItemTitle.text = item.title
        view.setOnClickListener {
            val intent = Intent(view.context, EventsDetailActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}
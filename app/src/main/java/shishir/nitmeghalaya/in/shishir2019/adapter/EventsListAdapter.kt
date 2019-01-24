package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.EventsListItemViewHolder


/**
 * Created by Devansh on 24/1/19.
 */

class EventsListAdapter : RecyclerView.Adapter<EventsListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListItemViewHolder =
        EventsListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event_list, parent, false))

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: EventsListItemViewHolder, position: Int) {
        holder.bind()
    }
}
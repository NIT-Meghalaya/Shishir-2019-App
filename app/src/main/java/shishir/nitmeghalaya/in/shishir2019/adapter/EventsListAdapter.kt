package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.EventsListItemViewHolder

class EventsListAdapter(private val ItemList: ArrayList<EventCard>, context: Context)
    : RecyclerView.Adapter<EventsListItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventsListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ItemList.size
    }

    override fun onBindViewHolder(holder: EventsListItemViewHolder, position: Int) {
        val item = ItemList[position]
        holder.bind(item)
    }

}
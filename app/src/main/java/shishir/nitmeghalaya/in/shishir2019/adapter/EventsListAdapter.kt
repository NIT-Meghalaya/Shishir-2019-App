package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.EventsListItemViewHolder

class EventsListAdapter(private val itemList: ArrayList<ShishirEvent>, private val context: Context)
    : RecyclerView.Adapter<EventsListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventsListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: EventsListItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        Glide.with(context).load(R.drawable.samplebg).into(holder.image)
    }

}
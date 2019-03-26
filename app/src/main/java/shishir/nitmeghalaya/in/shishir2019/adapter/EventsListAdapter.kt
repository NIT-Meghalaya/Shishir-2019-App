package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.createForegroundGradient
import shishir.nitmeghalaya.`in`.shishir2019.util.getImageResource
import shishir.nitmeghalaya.`in`.shishir2019.util.getTitleTextColorForImage
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.EventsListItemViewHolder

class EventsListAdapter(private val context: Context,
                        private val itemList: ArrayList<ShishirEvent>)
    : RecyclerView.Adapter<EventsListItemViewHolder>() {

    init {
        calculateForegroundGradientsForShishirEvents()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventsListItemViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: EventsListItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    //It helps in preventing lag on switching to EventsListFragment
    private fun calculateForegroundGradientsForShishirEvents() {
        itemList.forEach {
            it.foregroundGradient = createForegroundGradient(
                context, getImageResource(context,
                    if (it.image.isEmpty()) "krigg" else it.image) /*To prevent crash*/
            )
        }
    }

}
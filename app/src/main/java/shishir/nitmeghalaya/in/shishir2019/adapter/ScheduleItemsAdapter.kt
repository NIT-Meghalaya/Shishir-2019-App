package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.EventScheduleItem
import shishir.nitmeghalaya.`in`.shishir2019.util.getDominantImageColor
import shishir.nitmeghalaya.`in`.shishir2019.util.getImageResource
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.ScheduleItemViewHolder

/**
 * Created by Devansh on 24/3/19.
 */
class ScheduleItemsAdapter(private val scheduleItemsList: ArrayList<EventScheduleItem>,
                           private val context: Context): RecyclerView.Adapter<ScheduleItemViewHolder>() {

    init {
        scheduleItemsList.forEach {
            it.imageResId = getImageResource(context, it.image)
            it.dominantColor = getDominantImageColor(context, it.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_schedule, parent, false)
        return ScheduleItemViewHolder(view)
    }

    override fun getItemCount() = scheduleItemsList.size

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        holder.bind(scheduleItemsList[position])
    }
}
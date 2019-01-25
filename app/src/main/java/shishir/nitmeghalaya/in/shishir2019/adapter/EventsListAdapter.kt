package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_event_list.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.EventsListItemViewHolder


/**
 * Created by Devansh on 24/1/19.
 */

class EventsListAdapter(private val myDataset: Array<EventCard>) :
    RecyclerView.Adapter<EventsListAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): EventsListAdapter.MyViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_list, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val type = holder.layout.findViewById<TextView>(R.id.event_card_type)
        val title = holder.layout.findViewById<TextView>(R.id.event_card_title)
        val ec = myDataset[position]
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        type.setText(ec.type)
        title.setText(ec.title)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}
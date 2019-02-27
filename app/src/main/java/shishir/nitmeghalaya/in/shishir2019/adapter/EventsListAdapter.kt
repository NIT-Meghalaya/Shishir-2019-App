package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.activity.EventDescActivity
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard


class EventsListAdapter(private val ItemList: ArrayList<EventCard>, context: Context): RecyclerView.Adapter<EventsListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return ItemList.size
    }

    override fun onBindViewHolder(holder: EventsListAdapter.ViewHolder, position: Int) {
        val item = ItemList[position]
        holder.type.text = item.type
        holder.title.text = item.title
    }

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val type = view.findViewById(R.id.item_type) as TextView
        val title = view.findViewById(R.id.item_title) as TextView
        init{
            val position = adapterPosition as String
            view.setOnClickListener {
                val intent = Intent(view.context, EventDescActivity::class.java)
                intent.putExtra("Title", "title: " + position)
                intent.putExtra("Desc", "desc: " + position)
                intent.putExtra("Venue","vemue: " + position)
                intent.putExtra("Date", "date: " + position)
                view.context.startActivity(intent)
            }
        }
    }
}


/**
 * Created by Devansh on 24/1/19.

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
 */
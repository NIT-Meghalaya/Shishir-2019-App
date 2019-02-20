package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.EventsListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard

class EventsListActivity : AppCompatActivity(){

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val Items = ArrayList<EventCard>()
        for (i in 0..10)
            Items.add(EventCard("Type " + i, "Title " + i))

        val adapter = EventsListAdapter(Items)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        }
}




/*
class EventsListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var myDataset : Array<EventCard> = Array<EventCard>(10, fun (i: Int): EventCard {
        return EventCard("Type " + i, "Title " + i)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)

        viewManager = LinearLayoutManager(this)
        viewAdapter = EventsListAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }
}*/

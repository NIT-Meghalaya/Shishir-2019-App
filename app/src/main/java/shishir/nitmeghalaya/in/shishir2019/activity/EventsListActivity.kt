package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_events_list.*
import kotlinx.android.synthetic.main.event_item.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.EventsListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.EventCard

class EventsListActivity : AppCompatActivity(){

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        eventListItemImage
        val items = ArrayList<EventCard>()
        for (i in 0..10)
            items.add(EventCard("Type $i", "Title $i"))

        val adapter = EventsListAdapter(items,applicationContext)
        recyclerView.adapter = adapter
    }
}
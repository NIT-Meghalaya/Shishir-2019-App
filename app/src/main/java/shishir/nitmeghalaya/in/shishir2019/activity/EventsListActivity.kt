package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_events_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.EventsListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.DBConsts


class EventsListActivity : AppCompatActivity(), AnkoLogger {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        eventsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val eventsList = ArrayList<ShishirEvent>()

        db.collection(DBConsts.EVENTS)
            .get()
            .addOnSuccessListener {
                toast("Data received")
                for (document in it) {
                    eventsList.add(document.toObject(ShishirEvent::class.java))
                }
                val adapter = EventsListAdapter(eventsList, this)
                eventsRecyclerView.adapter = adapter
                Log.v("List", eventsList.toString())
            }
            .addOnFailureListener {
                toast("Error!")
            }

    }
}
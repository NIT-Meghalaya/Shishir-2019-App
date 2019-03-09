package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_event_desc.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.EVENT_DATA

class EventDetailActivity:AppCompatActivity() {

    lateinit var shishirEvent: ShishirEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_desc)

        shishirEvent = Gson().fromJson(intent.getStringExtra(EVENT_DATA), ShishirEvent::class.java)

        setSupportActionBar(toolbar)
        toolbar.title = shishirEvent.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_event_detail.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.EventDetailPagerAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.*
import android.view.MenuItem


class EventDetailActivity:AppCompatActivity() {

    lateinit var shishirEvent: ShishirEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)

        shishirEvent = Gson().fromJson(intent.getStringExtra(EVENT_DATA), ShishirEvent::class.java)
        shishirEvent.imageResId = getImageResource(this, shishirEvent.image)

        setUpViews()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpViews() {
        val dominantColor = getDominantImageColor(this, shishirEvent.imageResId)

        toolbar.title = shishirEvent.name
        Glide.with(this).load(shishirEvent.imageResId).into(eventImage)

        eventViewPager.adapter = EventDetailPagerAdapter(shishirEvent)
        eventViewPager.currentItem = 0

        collapsingToolbar.setContentScrimColor(dominantColor)
        collapsingToolbar.setStatusBarScrimColor(dominantColor)

        eventDetailTabs.setupWithViewPager(eventViewPager)

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            eventImage.foreground = createForegroundGradient(this, shishirEvent.imageResId)
        }
    }
}
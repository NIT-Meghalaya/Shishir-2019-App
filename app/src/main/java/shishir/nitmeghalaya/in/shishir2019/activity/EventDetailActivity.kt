package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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

            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)

                val text = "Check out _"+ shishirEvent.name + "_ and other awesome events.\n\n"+
                "*NIT Meghalaya* welcomes you to its annual cultural festival:\n\n" +
                        resources.getString(R.string.shishir_emoji)

                val appLink = "\n\nInstall the official app now:\n"+
                "https://play.google.com/store/apps/details?id=shishir.nitmeghalaya.in.shishir2019"
                shareIntent.putExtra(Intent.EXTRA_TEXT, text + appLink)

                shareIntent.type = "text/html"

                startActivity(Intent.createChooser(shareIntent, "Spread the word..."))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_share, menu)
        return super.onCreateOptionsMenu(menu)
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
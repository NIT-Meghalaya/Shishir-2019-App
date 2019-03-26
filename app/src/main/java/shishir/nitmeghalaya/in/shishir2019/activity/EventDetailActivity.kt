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

class EventDetailActivity:AppCompatActivity() {

    lateinit var shishirEvent: ShishirEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        shishirEvent = Gson().fromJson(intent.getStringExtra(EVENT_DATA), ShishirEvent::class.java)
        shishirEvent.imageResId = getImageResource(this, shishirEvent.image)

        setUpViews()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

//    @SuppressLint("CheckResult")
//    private fun setUpViews() {
//
//        val titleColor = getTitleTextColorForImage(this, imageResId)
////        val bodyColor = getBodyTextColorForImage(this, imageResId)
//
//        name.text = shishirEvent.name
//        name.setTextColor(titleColor)
//
//        val requestOptions = RequestOptions()
//        requestOptions.transform(
//            ColorFilterTransformation(getColorWithAddedAlpha(getDominantImageColor(this, imageResId), 0x66)),
//            BlurTransformation(22))
//        requestOptions.placeholder(imageResId)
//
//        Glide.with(this).load(imageResId)
//            .apply(requestOptions)
//            .into(backgroundImage)
//
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            backgroundImage.foreground = createForegroundGradient(this, imageResId)
//        }
//
//        descriptionTV.text = shishirEvent.description
//        //descriptionTV.setTextColor(bodyColor)
//
//        setUpToolbar()
//    }
//
//    private fun setUpToolbar() {
//        toolbarLayout.visibility = View.GONE
//    }
}
package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.toolbar.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.*

class EventDetailActivity:AppCompatActivity() {

    lateinit var shishirEvent: ShishirEvent
    var imageResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        shishirEvent = Gson().fromJson(intent.getStringExtra(EVENT_DATA), ShishirEvent::class.java)
        imageResId = getImageResource(this, shishirEvent.image)

        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpViews()
    }

    @SuppressLint("CheckResult")
    private fun setUpViews() {
        toolbarLayout.toolbarTitle.text = shishirEvent.name
        name.text = shishirEvent.name

        Glide.with(this).load(imageResId).into(backgroundImage)
//
//        val imageTintColor = getColorWithAddedAlpha(0xff, getDominantImageColor(this, imageResId))
//        backgroundImage.setColorFilter(imageTintColor, android.graphics.PorterDuff.Mode.ADD)
////        ImageViewCompat.setImageTintList(backgroundImage,
//////                ColorStateList.valueOf(
//////                        getColorWithAddedAlpha(0xaa, getDominantImageColor(this, imageResId))))
////

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            backgroundImage.foreground = createForegroundGradient(this, imageResId)
        }
    }
}
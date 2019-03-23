package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import jp.wasabeef.blurry.Blurry
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
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

        val requestOptions = RequestOptions()
        requestOptions.transforms(
            ColorFilterTransformation(getColorWithAddedAlpha(getDominantImageColor(this, imageResId), 0x66)),
            BlurTransformation(22))
        requestOptions.placeholder(imageResId)

        Glide.with(this).load(imageResId)
            .apply(requestOptions)
            .into(backgroundImage)

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            backgroundImage.foreground = createForegroundGradient(this, imageResId)
        }
    }
}
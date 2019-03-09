package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import jp.wasabeef.glide.transformations.BitmapTransformation
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_event_desc.*
import kotlinx.android.synthetic.main.toolbar.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.EVENT_DATA
import shishir.nitmeghalaya.`in`.shishir2019.util.createForegroundGradient
import shishir.nitmeghalaya.`in`.shishir2019.util.getImageResource

class EventDetailActivity:AppCompatActivity() {

    lateinit var shishirEvent: ShishirEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_desc)

        shishirEvent = Gson().fromJson(intent.getStringExtra(EVENT_DATA), ShishirEvent::class.java)

        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpViews()
    }

    @SuppressLint("CheckResult")
    private fun setUpViews() {
        toolbarLayout.toolbarTitle.text = shishirEvent.name
        name.text = shishirEvent.name


        val options = RequestOptions()
        options.transform(BlurTransformation(25, 3))

        Glide.with(applicationContext)
            .load(getImageResource(applicationContext, shishirEvent.image))
            //.apply(options)
            .into(backgroundImage)

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            backgroundImage.foreground = createForegroundGradient(
                applicationContext, getImageResource(applicationContext, shishirEvent.image))
        }
    }
}
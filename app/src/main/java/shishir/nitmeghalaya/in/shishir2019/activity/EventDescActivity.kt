package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import shishir.nitmeghalaya.`in`.shishir2019.R

class EventsDetailActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_desc)


        val back = findViewById(R.id.backbutton) as ImageView
        back.setOnClickListener {finish()}
    }
}
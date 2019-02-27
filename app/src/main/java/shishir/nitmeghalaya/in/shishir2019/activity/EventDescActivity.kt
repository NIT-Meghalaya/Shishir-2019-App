package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import shishir.nitmeghalaya.`in`.shishir2019.R

class EventDescActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_desc)

        //get data from intent


        // back button
        val back = findViewById(R.id.backbutton) as ImageView
        back.setOnClickListener {finish()}
    }
}
package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.jetbrains.anko.toast
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.fragment.EventsListFragment
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.COLLECTION_EVENTS
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eventsList = ArrayList<ShishirEvent>()

        db.collection(COLLECTION_EVENTS)
            .get()
            .addOnSuccessListener {
                toast("Data received")
//                loading_data_animation.pauseAnimation()
//                loading_data_animation.visibility = View.GONE
                for (document in it) {
                    eventsList.add(document.toObject(ShishirEvent::class.java))
                }
                addEventsListFragment(eventsList)
                Log.v("List", eventsList.toString())
            }
            .addOnFailureListener {
                toast("Error!")
            }
    }

    private fun addEventsListFragment(eventsList: ArrayList<ShishirEvent>) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder,
            EventsListFragment.newInstance(
                Gson().getJsonFromList<ArrayList<ShishirEvent>>(eventsList)))
        ft.commit()
    }
}
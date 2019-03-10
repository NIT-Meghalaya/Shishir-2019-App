package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.fragment.EventsListFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.ScheduleFragment
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.COLLECTION_EVENTS
import shishir.nitmeghalaya.`in`.shishir2019.util.createForegroundGradient
import shishir.nitmeghalaya.`in`.shishir2019.util.getImageResource
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList

class MainActivity : AppCompatActivity() ,
    EventsListFragment.EventsGradientsProvider {

    private val db = FirebaseFirestore.getInstance()
    private var eventsList = ArrayList<ShishirEvent>()
    private var eventsGradientsList = ArrayList<GradientDrawable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.collection(COLLECTION_EVENTS)
            .get()
            .addOnSuccessListener {
                toast("Data received")
                for (document in it) {
                    eventsList.add(document.toObject(ShishirEvent::class.java))
                }
                calculateForegroundGradientsForShishirEvents()
                addEventsListFragment()
                Log.v("List", eventsList.toString())
            }
            .addOnFailureListener {
                toast("Error!")
            }
        setUpBottomNavigation()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            bottomNavigationView.currentItem = 0
        }
    }

    override fun getEventsGradients(): ArrayList<GradientDrawable> {
        return eventsGradientsList
    }

    private fun addEventsListFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder,
            EventsListFragment.newInstance(
                Gson().getJsonFromList<ArrayList<ShishirEvent>>(eventsList)))
        ft.commit()
    }

    private fun calculateForegroundGradientsForShishirEvents() {
        for (event in eventsList) {
            eventsGradientsList.add(createForegroundGradient(
                applicationContext, getImageResource(applicationContext,
                    if (event.image.isEmpty()) "krigg" else event.image)))
        }
    }

    private fun setUpBottomNavigation() {

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val ft = supportFragmentManager.beginTransaction()

                when (it.itemId) {
                    R.id.action_events_list -> {
                        if (currentItem != 0) {
                            ft.replace(R.id.fragment_placeholder, EventsListFragment.newInstance(
                                Gson().getJsonFromList<ArrayList<ShishirEvent>>(eventsList)))
                        }
                    }

                    R.id.action_schedule -> {
                        if (currentItem != 1) {
                            ft.replace(R.id.fragment_placeholder, ScheduleFragment.newInstance())
                                .addToBackStack("schedule")
                        }
                    }

                }
                ft.commit()
                true
            }
        }
    }
}
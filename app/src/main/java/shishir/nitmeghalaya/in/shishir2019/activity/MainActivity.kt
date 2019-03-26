package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar_layout.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.toast
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.fragment.EventsListFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.ScheduleFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.SponsorListFragment
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.*

class MainActivity : AppCompatActivity() ,
    EventsListFragment.EventsListItemsColorsProvider {

    private val db = FirebaseFirestore.getInstance()
    private var eventsList = ArrayList<ShishirEvent>()
    private var eventsGradientsList = ArrayList<GradientDrawable>()
    private var eventsTitleColorsList = ArrayList<Int>()

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

    override fun getEventsTitleColors(): ArrayList<Int> {
        for (event in eventsList) {
            eventsTitleColorsList.add(
                getTitleTextColorForImage(this,
                    getImageResource(this, if (event.image.isEmpty()) "krigg" else event.image))
            )
        }
        return eventsTitleColorsList
    }

    private fun addEventsListFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder,
            EventsListFragment.newInstance(
                Gson().getJsonFromList<ArrayList<ShishirEvent>>(eventsList)))
        ft.commit()
    }

    //It helps in preventing lag on switching to EventsListFragment
    private fun calculateForegroundGradientsForShishirEvents() {
        for (event in eventsList) {
            eventsGradientsList.add(createForegroundGradient(
                this, getImageResource(this,
                    if (event.image.isEmpty()) "krigg" else event.image)))
        }
    }

    private fun setUpBottomNavigation() {

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                val ft = supportFragmentManager.beginTransaction()

                when (it.itemId) {
                    R.id.action_events_list -> {
                        if (currentItem != 0) {
                            appBar.visibility = View.VISIBLE
                            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                            ft.replace(R.id.fragment_placeholder, EventsListFragment.newInstance(
                                Gson().getJsonFromList<ArrayList<ShishirEvent>>(eventsList)))
                        }
                    }

                    R.id.action_schedule -> {
                        if (currentItem != 1) {
                            appBar.visibility = View.GONE
                            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                            ft.replace(R.id.fragment_placeholder, ScheduleFragment.newInstance())
                                .addToBackStack("schedule")
                        }
                    }

                    R.id.action_sponsor-> {
                        if(currentItem != 2) {
                            appBar.visibility = View.GONE
                            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                            ft.replace(R.id.fragment_placeholder, SponsorListFragment.newInstance()).addToBackStack("sponsors")
                        }
                    }
                }
                ft.commit()
                true
            }
        }
    }
}
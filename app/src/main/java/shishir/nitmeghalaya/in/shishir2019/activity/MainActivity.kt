package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.fragment.EventsListFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.ScheduleFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.SponsorListFragment
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.*

class MainActivity : AppCompatActivity() {

    private val fragmentsMap = mutableMapOf<String, Fragment>()

    companion object {
        const val FRAGMENT_EVENTS = "events"
        const val FRAGMENT_SCHEDULE = "shedule"
        const val FRAGMENT_TEAM = "team"
        const val FRAGMENT_SPONSORS = "sponsors"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addEventsListFragment()
        setUpBottomNavigation()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            bottomNavigationView.currentItem = 0
        } else {
            super.onBackPressed()
        }
    }

    private fun addEventsListFragment() {
        if (fragmentsMap[FRAGMENT_EVENTS] == null)
            fragmentsMap[FRAGMENT_EVENTS] = EventsListFragment.newInstance()
        else {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_placeholder, fragmentsMap[FRAGMENT_EVENTS]!!)
            ft.commit()
        }
    }

    private fun addScheduleFragment() {
        if (fragmentsMap[FRAGMENT_SCHEDULE] == null)
            fragmentsMap[FRAGMENT_SCHEDULE] = ScheduleFragment.newInstance()
        else {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_placeholder, fragmentsMap[FRAGMENT_SCHEDULE]!!)
                .addToBackStack(FRAGMENT_SCHEDULE)
            ft.commit()
        }
    }

    private fun addTeamFragment() {
        if (fragmentsMap[FRAGMENT_TEAM] == null)
            fragmentsMap[FRAGMENT_TEAM] = ScheduleFragment.newInstance()
        else {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_placeholder, fragmentsMap[FRAGMENT_TEAM]!!)
                .addToBackStack(FRAGMENT_TEAM)
            ft.commit()
        }
    }

    private fun addSponsorsFragment() {
        if (fragmentsMap[FRAGMENT_SPONSORS] == null)
            fragmentsMap[FRAGMENT_SPONSORS] = ScheduleFragment.newInstance()
        else {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_placeholder, fragmentsMap[FRAGMENT_SPONSORS]!!)
                .addToBackStack(FRAGMENT_SPONSORS)
            ft.commit()
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
                            addEventsListFragment()
                        }
                    }

                    R.id.action_schedule -> {
                        if (currentItem != 1) {
                            appBar.visibility = View.GONE
                            addScheduleFragment()
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
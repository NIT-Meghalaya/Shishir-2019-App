package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.fragment.EventsListFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.ScheduleFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.SponsorListFragment
import shishir.nitmeghalaya.`in`.shishir2019.fragment.TeamFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_EVENTS = "events"
        const val FRAGMENT_SCHEDULE = "shedule"
        const val FRAGMENT_TEAM = "team"
        const val FRAGMENT_SPONSORS = "sponsors"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_placeholder, EventsListFragment.newInstance())
            .commit()

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

    private fun setUpBottomNavigation() {

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                val ft = supportFragmentManager.beginTransaction()

                when (it.itemId) {
                    R.id.action_events_list -> {
                        if (currentItem != 0) {
                            appBar.visibility = View.VISIBLE
                            ft.replace(R.id.fragment_placeholder, EventsListFragment.newInstance())
                        }
                    }

                    R.id.action_schedule -> {
                        if (currentItem != 1) {
                            appBar.visibility = View.GONE
                            ft.replace(R.id.fragment_placeholder, ScheduleFragment.newInstance())
                                .addToBackStack(FRAGMENT_SCHEDULE)
                        }
                    }

                    R.id.action_team -> {
                        if (currentItem != 2) {
                            appBar.visibility = View.VISIBLE
                            ft.replace(R.id.fragment_placeholder, TeamFragment.newInstance())
                                    .addToBackStack(FRAGMENT_TEAM)
                        }
                    }

                    R.id.action_sponsor-> {
                        if(currentItem != 3) {
                            appBar.visibility = View.VISIBLE
                            ft.replace(R.id.fragment_placeholder, SponsorListFragment.newInstance())
                                .addToBackStack(FRAGMENT_SPONSORS)
                        }
                    }
                }
                ft.commit()
                true
            }
        }
    }
}
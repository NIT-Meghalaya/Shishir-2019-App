package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.appbar_layout.view.*
import shishir.nitmeghalaya.`in`.shishir2019.fragment.*
import shishir.nitmeghalaya.`in`.shishir2019.uiutils.LoadingAnimationController
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : AppCompatActivity(), LoadingAnimationController {

    companion object {
        const val FRAGMENT_EVENTS = "events"
        const val FRAGMENT_SCHEDULE = "shedule"
        const val FRAGMENT_TEAM = "team"
        const val FRAGMENT_SPONSORS = "sponsors"
        const val FRAGMENT_CONTACT = "contact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)

        //Log.d("Instance ID", FirebaseInstanceId.getInstance().getId())

        val t = Thread(Runnable {
            val getPrefs = PreferenceManager
                .getDefaultSharedPreferences(baseContext)

            val isFirstStart = getPrefs.getBoolean("firstStart", true)

            if (isFirstStart) {
                val i = Intent(this@MainActivity, IntroSliderActivity::class.java)
                runOnUiThread { startActivity(i) }
                val e = getPrefs.edit()
                e.putBoolean("firstStart", false)
                e.apply()
            }
        })
        t.start()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_placeholder, EventsListFragment.newInstance())
            .commit()

        val params = appBar.toolbarLayout.layoutParams
                as AppBarLayout.LayoutParams
        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
        setUpBottomNavigation()
        setUpShareOption()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            bottomNavigationView.currentItem = 0
        } else {
            super.onBackPressed()
        }
    }

    override fun hideLoadingAnimation() {
        loading_data_animation.pauseAnimation()
        loading_data_animation.visibility = View.GONE
    }

    override fun showLoadingAnimation() {
        loading_data_animation.playAnimation()
        loading_data_animation.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)

                val text = "*NIT Meghalaya* welcomes you to its annual cultural festival:\n\n" +
                        resources.getString(R.string.shishir_emoji)

                val appLink = "\n\nInstall the official app now:\n"+
                        "https://play.google.com/store/apps/details?id=shishir.nitmeghalaya.in.shishir2019"
                shareIntent.putExtra(Intent.EXTRA_TEXT, text + appLink)

                shareIntent.type = "text/html"

                startActivity(Intent.createChooser(shareIntent, "Spread the word..."))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpShareOption() {

        appBar.toolbarLayout.shareButton.setOnClickListener {

            val shareIntent = Intent(Intent.ACTION_SEND)

            val text = "*NIT Meghalaya* welcomes you to its annual cultural festival:\n\n" +
                    resources.getString(R.string.shishir_emoji)

            val appLink = "\n\nInstall the official app now:\n" +
                    "https://play.google.com/store/apps/details?id=shishir.nitmeghalaya.in.shishir2019"
            shareIntent.putExtra(Intent.EXTRA_TEXT, text + appLink)

            shareIntent.type = "text/html"

            startActivity(Intent.createChooser(shareIntent, "Spread the word..."))
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
                            val params = appBar.toolbarLayout.layoutParams
                                    as AppBarLayout.LayoutParams
                            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
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
                            val params = appBar.toolbarLayout.layoutParams
                                    as AppBarLayout.LayoutParams
                            params.scrollFlags = 0
                            ft.replace(R.id.fragment_placeholder, TeamFragment.newInstance())
                                    .addToBackStack(FRAGMENT_TEAM)
                        }
                    }

                    R.id.action_sponsor-> {
                        if(currentItem != 3) {
                            appBar.visibility = View.VISIBLE
                            val params = appBar.toolbarLayout.layoutParams
                                    as AppBarLayout.LayoutParams
                            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                            ft.replace(R.id.fragment_placeholder, SponsorListFragment.newInstance())
                                .addToBackStack(FRAGMENT_SPONSORS)
                        }
                    }

                    R.id.action_contact-> {
                        if(currentItem != 4) {
                            appBar.visibility = View.VISIBLE
                            val params = appBar.toolbarLayout.layoutParams
                                    as AppBarLayout.LayoutParams
                            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                            ft.replace(R.id.fragment_placeholder, ContactFragment.newInstance())
                                .addToBackStack(FRAGMENT_CONTACT)
                        }
                    }
                }
                ft.commit()
                true
            }
        }
    }
}
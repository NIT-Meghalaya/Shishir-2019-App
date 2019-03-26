package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.SchedulePagerAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.EventScheduleItem
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider.Companion.DAY_1
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider.Companion.DAY_2
import shishir.nitmeghalaya.`in`.shishir2019.util.makeShortToast

class ScheduleFragment : Fragment(), ScheduleProvider {

    var scheduleDay1 = arrayListOf<EventScheduleItem>()
    var scheduleDay2 = arrayListOf<EventScheduleItem>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        getScheduleFromDatabase(view.viewPager)
        return view
    }

    override fun getSchedule(day: String) = when(day) {
             DAY_1 -> scheduleDay1
             DAY_2 -> scheduleDay2
             else -> ArrayList()
        }

    private fun getScheduleFromDatabase(viewPager: ViewPager) {
        val db = FirebaseFirestore.getInstance()
        db.collection("schedule").document("schedule").get()
            .addOnSuccessListener { documentSnapshot ->
                val map = documentSnapshot.data
                Log.v("data", map.toString())
                map?.forEach {
                    when (it.key) {
                        DAY_1 -> scheduleDay1 = it.value as ArrayList<EventScheduleItem>
                        DAY_2 -> scheduleDay2 = it.value as ArrayList<EventScheduleItem>
                    }
                }
                Log.v("schedule1", scheduleDay1.toString())
                Log.v("schedule2", scheduleDay2.toString())
                //makeShortToast(context!!, "Schedule is ready !")
                setUpViewPagerAndTabs(viewPager)
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleFragment()
    }

    private fun setUpViewPagerAndTabs(viewPager: ViewPager) {
        viewPager.adapter = SchedulePagerAdapter(fragmentManager!!, this@ScheduleFragment)
        viewPager.currentItem = 0

        tabLayout.visibility = View.VISIBLE
        tabLayout.setupWithViewPager(viewPager)
    }
}

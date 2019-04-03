package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.content.Context
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
import shishir.nitmeghalaya.`in`.shishir2019.uiutils.LoadingAnimationController
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider.Companion.DAY_1
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider.Companion.DAY_2
import java.lang.RuntimeException

class ScheduleFragment : Fragment(), ScheduleProvider {

    private var animationController: LoadingAnimationController? = null
    var scheduleDay1 = arrayListOf<EventScheduleItem>()

    var scheduleDay2 = arrayListOf<EventScheduleItem>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        animationController?.showLoadingAnimation()
        getScheduleFromDatabase(view.viewPager)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is LoadingAnimationController)
            animationController = activity as LoadingAnimationController
        else
            throw RuntimeException("Activity not a LoadingAnimationController")
    }

    override fun onDetach() {
        super.onDetach()
        animationController = null
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
                map?.forEach {
                    when (it.key) {
                        DAY_1 -> scheduleDay1 = it.value as ArrayList<EventScheduleItem>
                        DAY_2 -> scheduleDay2 = it.value as ArrayList<EventScheduleItem>
                    }
                }
                setUpViewPagerAndTabs(viewPager)
                if (!isDetached)
                    animationController?.hideLoadingAnimation()

                scheduleDay1.clear()
                scheduleDay1.add(EventScheduleItem("Inauguration", "10:00", "11:30", image = "shishir_logo"))
                scheduleDay1.add(EventScheduleItem("SPIC MACAY", "11:30", "13:00", image = "spic_macay"))
                scheduleDay1.add(EventScheduleItem("Rangoli", "14:00", "16:00", image = "rangoli"))
                scheduleDay1.add(EventScheduleItem("Treasure Hunt", "14:00", "16:00", image = "treasure_hunt"))
                scheduleDay1.add(EventScheduleItem("LAN Gaming", "14:00", "16:00", image = "lan_gaming"))
                scheduleDay1.add(EventScheduleItem("Harmony", "16:00", "18:30", image = "harmony"))
                scheduleDay1.add(EventScheduleItem("Pronite", "18:30", "21:00", image = "pronite"))

                scheduleDay2.add(4, EventScheduleItem("Dramatics", "13:00", "14:00", "6th April", "dramatics"))
                scheduleDay2.add(5, EventScheduleItem("Step Up", "14:00", "16:00", "6th April", "step_up"))

                val mMap = mutableMapOf<String, ArrayList<EventScheduleItem>>()
                mMap[DAY_2] = scheduleDay2
                mMap[DAY_1] = scheduleDay1

                Log.v("schedule", scheduleDay2.toString())
                //db.collection("schedule").document("schedule").set(mMap)
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

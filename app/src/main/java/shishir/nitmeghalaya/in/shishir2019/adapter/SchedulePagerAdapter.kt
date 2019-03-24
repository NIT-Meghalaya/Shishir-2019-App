package shishir.nitmeghalaya.`in`.shishir2019.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.gson.Gson
import shishir.nitmeghalaya.`in`.shishir2019.fragment.DayScheduleFragment
import shishir.nitmeghalaya.`in`.shishir2019.models.EventScheduleItem
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider.Companion.DAY_1
import shishir.nitmeghalaya.`in`.shishir2019.util.ScheduleProvider.Companion.DAY_2
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList

/**
 * Created by Devansh on 24/3/19.
 */
class SchedulePagerAdapter(fm: FragmentManager, provider: ScheduleProvider)
    : FragmentStatePagerAdapter(fm) {

    private val scheduleDay1 = provider.getSchedule(DAY_1)
    private val scheduleDay2 = provider.getSchedule(DAY_2)

    override fun getItem(position: Int) =
        when(position) {
            0 -> DayScheduleFragment.newInstance(getScheduleAsJson(scheduleDay1))
            1 -> DayScheduleFragment.newInstance(getScheduleAsJson(scheduleDay2))
            else -> DayScheduleFragment.newInstance(getScheduleAsJson(scheduleDay1))
        }

    override fun getCount() = 2

    private fun getScheduleAsJson(scheduleArrayList: ArrayList<EventScheduleItem>): String =
        Gson().getJsonFromList<ArrayList<EventScheduleItem>>(scheduleArrayList)
}
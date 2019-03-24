package shishir.nitmeghalaya.`in`.shishir2019.util

import shishir.nitmeghalaya.`in`.shishir2019.models.EventScheduleItem

/**
 * Created by Devansh on 10/3/19.
 */

interface ScheduleProvider {

    companion object {
        const val DAY_1 = "day1"
        const val DAY_2 = "day2"
    }

    fun getSchedule(day: String): ArrayList<EventScheduleItem>
}
package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.EventScheduleItem
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson

class DayScheduleFragment : Fragment() {

    private lateinit var schedule: ArrayList<EventScheduleItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            schedule = Gson().getListFromJson<ArrayList<EventScheduleItem>>(
                it.getString(SCHEDULE)!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_day_schedule,
                container, false)

        view.apply {
        }

        return view
    }


    companion object {

        private const val SCHEDULE = "schedule"

        @JvmStatic
        fun newInstance(schedule: String) =
                DayScheduleFragment().apply {
                    arguments = Bundle().apply {
                        putString(SCHEDULE, schedule)
                     }
                }
    }
}

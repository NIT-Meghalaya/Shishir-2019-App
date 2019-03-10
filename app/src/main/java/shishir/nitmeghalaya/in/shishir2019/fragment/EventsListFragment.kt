package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_events_list.view.*

import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.EventsListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson

class EventsListFragment : Fragment() {

    private lateinit var eventsList: ArrayList<ShishirEvent>

    companion object {

        private const val EVENTS_LIST = "events list"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance(eventsList: String) =
            EventsListFragment().apply {
                arguments = Bundle().apply {
                    putString(EVENTS_LIST, eventsList)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            eventsList = Gson().getListFromJson<ArrayList<ShishirEvent>>(
                getString(EVENTS_LIST)!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_events_list, container, false)
        view.apply {
            eventsListRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = EventsListAdapter(eventsList)
            }
        }
        return view
    }
}

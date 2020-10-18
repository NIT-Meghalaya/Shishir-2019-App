package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_events_list.*
import kotlinx.android.synthetic.main.fragment_events_list.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.EventsListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.uiutils.LoadingAnimationController
import shishir.nitmeghalaya.`in`.shishir2019.util.COLLECTION_EVENTS
import java.lang.RuntimeException

class EventsListFragment : Fragment() {

    private var eventsList = arrayListOf<ShishirEvent>()
    private var animationController: LoadingAnimationController? = null

    companion object {
        @JvmStatic
        fun newInstance() = EventsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_events_list, container, false)
        animationController?.showLoadingAnimation()
        getScheduleFromDatabase(view.eventsListRecyclerView)
        return view
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (activity is LoadingAnimationController)
//            animationController = activity as LoadingAnimationController
//        else
//            throw RuntimeException("Activity not a LoadingAnimationController")
//    }

    override fun onDetach() {
        super.onDetach()
        animationController = null
    }

    private fun getScheduleFromDatabase(recyclerView: RecyclerView) {
        val db = FirebaseFirestore.getInstance()

        db.collection(COLLECTION_EVENTS)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    eventsList.add(document.toObject(ShishirEvent::class.java))
                }
                eventsList.sortBy {shishirEvent ->
                    shishirEvent.priority
                }
                Log.v("List", eventsList.toString())

                setUpRecyclerView(recyclerView)
                if (!isDetached)
                    animationController?.hideLoadingAnimation()
            }
            .addOnFailureListener {
                Log.v("error", "error")
            }
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = EventsListAdapter(context, eventsList)
        }
    }
}

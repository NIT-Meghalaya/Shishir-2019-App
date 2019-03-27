package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sponsor_list.*
import kotlinx.android.synthetic.main.fragment_sponsor_list.view.*

import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.SponsorListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.SponsorItem
import shishir.nitmeghalaya.`in`.shishir2019.util.SPONSOR_LIST
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson
import shishir.nitmeghalaya.`in`.shishir2019.util.makeShortToast

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SponsorListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SponsorListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SponsorListFragment : Fragment() {

    private var sponsorList = ArrayList<SponsorItem>()
    val db = FirebaseFirestore.getInstance()

    companion object {
        @JvmStatic
        fun newInstance() = SponsorListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View
    {
        val view = inflater.inflate(R.layout.fragment_sponsor_list, container, false)
        getSponsors(view)

        return view
    }

    private fun getSponsors(view: View) {
        db.collection(SPONSOR_LIST).get()
            .addOnSuccessListener {
                makeShortToast(context!!,"loaded")
                for (document in it) {
                    sponsorList.add(document.toObject(SponsorItem::class.java))
                }

                view.apply {
                    sponsorsListRecyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = SponsorListAdapter(sponsorList)
                    }
                }

                loading_data_animation.visibility = View.GONE

            }.addOnFailureListener {
                makeShortToast(context!!,"error")
            }


    }


}


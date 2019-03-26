package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sponsor_list.view.*

import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.SponsorListAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.SponsorItem
import shishir.nitmeghalaya.`in`.shishir2019.util.SPONSOR_LIST
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson

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

    private lateinit var sponsorList: ArrayList<SponsorItem>

    companion object {
        @JvmStatic
        fun newInstance() = SponsorListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            sponsorList = Gson().getListFromJson<ArrayList<SponsorItem>>(
                getString(SPONSOR_LIST)!!
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View
    {
        val view = inflater.inflate(R.layout.fragment_sponsor_list, container, false)
        view.apply {
            sponsorsListRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = SponsorListAdapter(sponsorList)
                }
            }

        return view
        }

    }


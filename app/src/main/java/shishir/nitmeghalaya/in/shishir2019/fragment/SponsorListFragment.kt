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
import shishir.nitmeghalaya.`in`.shishir2019.uiutils.LoadingAnimationController
import shishir.nitmeghalaya.`in`.shishir2019.util.SPONSOR_LIST
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson
import shishir.nitmeghalaya.`in`.shishir2019.util.makeShortToast
import java.lang.RuntimeException

class SponsorListFragment : Fragment() {

    private var sponsorList = ArrayList<SponsorItem>()
    private var animationController: LoadingAnimationController? = null

    val db = FirebaseFirestore.getInstance()
    companion object {
        @JvmStatic
        fun newInstance() = SponsorListFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        val view = inflater.inflate(R.layout.fragment_sponsor_list, container, false)
        animationController?.showLoadingAnimation()
        getSponsors(view)

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
                if (!isDetached)
                    animationController?.hideLoadingAnimation()
            }.addOnFailureListener {
                makeShortToast(context!!,"error")
            }


    }


}


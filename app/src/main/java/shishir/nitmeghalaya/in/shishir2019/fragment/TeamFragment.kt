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
import com.google.gson.Gson

import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.TeamMembersRecyclerViewAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember
import shishir.nitmeghalaya.`in`.shishir2019.uiutils.LoadingAnimationController
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson
import java.lang.RuntimeException

class TeamFragment : Fragment() {

    private var animationController: LoadingAnimationController? = null

    companion object {
        @JvmStatic
        fun newInstance() = TeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val recyclerView = inflater.inflate(
                R.layout.fragment_team, container, false) as RecyclerView
        animationController?.showLoadingAnimation()
        getTeamMembersFromDatabase(recyclerView)
        return recyclerView
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


    private fun getTeamMembersFromDatabase(recyclerView: RecyclerView) {
        var teamMembersList: ArrayList<TeamMember> = arrayListOf()

        val db = FirebaseFirestore.getInstance()
        db.collection("coreTeams").document("newTeamMembers").get()
                .addOnSuccessListener {documentSnapshot ->
                    documentSnapshot.data?.forEach {
                        teamMembersList = makeTeamMembersList(it.value as ArrayList<TeamMember>)
                    }
                    recyclerView.apply {
                        adapter = TeamMembersRecyclerViewAdapter(context, teamMembersList)
                        layoutManager = LinearLayoutManager(context)
                    }
                    if (!isDetached)
                        animationController?.hideLoadingAnimation()

//                    addNewTeamMembers(teamMembersList)
//                    val map = mutableMapOf<String, ArrayList<TeamMember>>()
//                    map["members"] = teamMembersList
//                    db.collection("coreTeams").document("newTeamMembers").set(map)
//                        .addOnSuccessListener {
//                            Log.v("data added", teamMembersList.toString())
//                        }
                }
    }

    private fun makeTeamMembersList(teamMembersListMap: ArrayList<TeamMember>): ArrayList<TeamMember> {
        val json = Gson().getJsonFromList<ArrayList<TeamMember>>(teamMembersListMap)
        return Gson().getListFromJson(json)
    }
}

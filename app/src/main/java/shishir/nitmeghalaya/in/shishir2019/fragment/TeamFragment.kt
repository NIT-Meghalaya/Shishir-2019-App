package shishir.nitmeghalaya.`in`.shishir2019.fragment


import android.os.Bundle
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
import shishir.nitmeghalaya.`in`.shishir2019.util.getJsonFromList
import shishir.nitmeghalaya.`in`.shishir2019.util.getListFromJson

class TeamFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val recyclerView = inflater.inflate(
                R.layout.fragment_team, container, false) as RecyclerView
        getTeamMembersFromDatabase(recyclerView)
        return recyclerView
    }

    private fun getTeamMembersFromDatabase(recyclerView: RecyclerView) {
        val db = FirebaseFirestore.getInstance()
        db.collection("coreTeams").document("teamMembers").get()
                .addOnSuccessListener {documentSnapshot ->
                    var teamMembersList: ArrayList<TeamMember> = arrayListOf()
                    documentSnapshot.data?.forEach {
                        teamMembersList = makeTeamMembersList(it.value as ArrayList<TeamMember>)
                    }
                    recyclerView.apply {
                        adapter = TeamMembersRecyclerViewAdapter(context, teamMembersList)
                        layoutManager = LinearLayoutManager(context)
                    }
                }
    }

    private fun makeTeamMembersList(teamMembersListMap: ArrayList<TeamMember>): ArrayList<TeamMember> {
        val json = Gson().getJsonFromList<ArrayList<TeamMember>>(teamMembersListMap)
        return Gson().getListFromJson(json)
    }
}

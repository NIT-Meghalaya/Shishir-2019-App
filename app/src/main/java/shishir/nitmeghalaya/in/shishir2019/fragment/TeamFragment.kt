package shishir.nitmeghalaya.`in`.shishir2019.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.adapter.TeamMembersRecyclerViewAdapter
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember

class TeamFragment : Fragment() {

    private var teamMembersList = arrayListOf<TeamMember>()

    companion object {
        @JvmStatic
        fun newInstance() = TeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val recyclerView = inflater.inflate(
                R.layout.fragment_team, container, false) as RecyclerView
        getTeamMembersFromDatabase(recyclerView)
        return view
    }

    private fun getTeamMembersFromDatabase(recyclerView: RecyclerView) {
        val db = FirebaseFirestore.getInstance()
        db.collection("coreTeams").document("teamMembers").get()
                .addOnSuccessListener {documentSnapshot ->
                    documentSnapshot.data?.forEach {
                        teamMembersList = it.value as ArrayList<TeamMember>
                    }
                    recyclerView.apply {
                        adapter = TeamMembersRecyclerViewAdapter(context, teamMembersList)
                        layoutManager = LinearLayoutManager(context)
                    }
                }
    }
}

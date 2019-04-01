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


    private fun getTeamMembersFromDatabase(recyclerView: RecyclerView) {
        var teamMembersList: ArrayList<TeamMember> = arrayListOf()

        val db = FirebaseFirestore.getInstance()
        db.collection("coreTeams").document("teamMembers").get()
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

    private fun addNewTeamMembers(members: ArrayList<TeamMember>) {
        val coordinator = "Coordinator"
        val cocoordinator = "Co-coordinator"

        members.add(27, TeamMember("Chiluveru Gnaneshwar", coordinator, "chilu.gnaneshwar143@gmail.com", "9676726733", "chiluveru_gnaneshwar", team = "Event Management"))
        members.add(28, TeamMember("S.N. Charan Reddy", coordinator, "crcharanreddy425@gmail.com", "6305350173", "s_n_charan_reddy", team = "Event Management"))
        members.add(29, TeamMember("Md. Yusuf Zuhar Mohommed Rafad", cocoordinator, "rafadzuhar@gmail.com", "7641942552", "rafad_zuhar", team = "Event Management"))
        members.add(30, TeamMember("Kishan Lal", cocoordinator, "kishangarg9410@gmail.com", "7412921030", "kishan_lal", team = "Event Management"))
        members.add(31, TeamMember("Saurabh Singh", cocoordinator, "saurabhsinghooo79@gmail.com", "8174058896", "saurabh_singh", team = "Event Management"))
        members.add(32, TeamMember("Bollampalli Satya Abhinay", coordinator, "9491578030d@gmail.com", "9494255663", "bollampalli_satya_abhinay", team = "Designing"))
        members.add(33, TeamMember("V. Sai Kishore Chary", coordinator, "Nikhilskchary@gmail.com", "7005186650", "v_sai_lishore_chary", team = "Designing"))
        members.add(34, TeamMember("Kummari Tirupathi Rao", coordinator, "tirupathi9436@gmail.com", "9010640342", "kummari_tirupathi_rao", team = "Designing"))
        members.add(35, TeamMember("Kham Muan Lian", cocoordinator, "kaeemel5@gmail.com", "8730915224", "kham_muan_lian", team = "Designing"))
        members.add(36, TeamMember("Nathaneal Gympad", cocoordinator, "nerzeck17@gmail.com", "8837455943", "nathaneal_gympad", team = "Designing"))
        members.add(37, TeamMember("Sanapala Rajesh", cocoordinator, "sanapalarajesh04@gmail.com", "8985425080", "sanapala_rajesh", team = "Designing"))
        members.add(38, TeamMember("Rasalinda Diengdoh", coordinator, "rasalinda246@gmail.com", "9366425286", "rasalinda_diengdoh", team = "Publicity"))
        members.add(39, TeamMember("Rafaela Nongrum", coordinator, "rafunongrum@gmail.com", "7085054477", "rafaela_nongrum", team = "Publicity"))
        members.add(40, TeamMember("Donnagratia Syndor", coordinator, "donnagratia@gmail.com", "8413075511", "donnagratia_syndor", team = "Hospitality"))
        members.add(41, TeamMember("Elvarie Nongspung", coordinator, "enongspung@gmail.com", "7308330940", "elvarie_nongspung", team = "Hospitality"))
        members.add(42, TeamMember("Dianglinshisha Marbaninang", coordinator, "dianglin12@gmail.com", "8259968742", "dianglinshisha_marbaniang", team = "Hospitality"))
        members.add(43, TeamMember("Michelle Mukhim", cocoordinator, "mukhimichelle@gmail.com", "7005913605", "michelle_mukhim", team = "Hospitality"))
        members.add(44, TeamMember("Maurizio Lean Delano Shadap", cocoordinator, "maauriziodelano19@gmail.com", "maurizio_lean_delano_shadap", "9774406492", team = "Hospitality"))
        members.add(45, TeamMember("Kagaba Dennis", cocoordinator, "denniskag@gmail.com", "8787716417", "kagaba_dennis", team = "Hospitality"))
        members.add(46, TeamMember("Eatha Viswa Teja", coordinator, "vtejaeta@gmail.com", "9493541749", "eatha_viswa_teja", team = "Stage Management"))
        members.add(47, TeamMember("K. Damodar Reddy", coordinator, "bhairavadamodar@gmail.com", "7989021487", "k_damodar_reddy", team = "Stage Management"))
        members.add(48, TeamMember("Khushi Mishra", coordinator, "khushimishra1104@gmail.com", "8979859441", "khushi_mishra", team = "Stage Management"))
        members.add(49, TeamMember("Ananya Giri", coordinator, "ananyagiri1098@gmail.com", "8730806513", "ananya_giri", team = "Stage Management"))

    }

    private fun makeTeamMembersList(teamMembersListMap: ArrayList<TeamMember>): ArrayList<TeamMember> {
        val json = Gson().getJsonFromList<ArrayList<TeamMember>>(teamMembersListMap)
        return Gson().getListFromJson(json)
    }
}

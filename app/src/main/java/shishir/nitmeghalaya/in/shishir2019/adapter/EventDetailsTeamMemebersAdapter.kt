package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.EventDetailTeamMemberViewHolder

/**
 * Created by Devansh on 26/3/19.
 */
class EventDetailsTeamMemebersAdapter(private val teamMembers: List<TeamMember>):
        RecyclerView.Adapter<EventDetailTeamMemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailTeamMemberViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_team_member_in_event_details, parent, false)
        return EventDetailTeamMemberViewHolder(view)
    }

    override fun getItemCount() = teamMembers.size

    override fun onBindViewHolder(holder: EventDetailTeamMemberViewHolder, position: Int) {
        holder.bind(teamMembers[position])
    }
}
package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_team_member_in_event_details.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember

/**
 * Created by Devansh on 26/3/19.
 */
class EventDetailTeamMemberViewHolder(view: View): BaseViewHolder(view) {

    override fun bind(item: Any) {
        item as TeamMember

        view.apply {
            name.text = item.name
            post.text = item.post
            //Glide.with(context).load(item.imageResId).into(imageView)
        }
    }
}
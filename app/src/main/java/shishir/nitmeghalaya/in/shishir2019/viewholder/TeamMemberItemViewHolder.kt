package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_team_member.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember

/**
 * Created by Devansh on 26/3/19.
 */
class TeamMemberItemViewHolder(view: View): BaseViewHolder(view) {

    override fun bind(item: Any) {
        item as TeamMember

        view.apply {
            name.text = item.name
            post.text = "${item.post} (${item.team})"
            email.text = item.email
            contact.text = item.contact
            Glide.with(view).load(item.imageResId).into(memberImageView)
        }
    }
}
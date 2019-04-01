package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_team_member.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember
import android.content.Intent
import android.net.Uri

/**
 * Created by Devansh on 26/3/19.
 */
class TeamMemberItemViewHolder(view: View): BaseViewHolder(view) {

    override fun bind(item: Any) {
        item as TeamMember
        view.apply {
            name.text = item.name

            if (item.team.isNotEmpty())
                post.text = "${item.post} (${item.team})"
            else
                post.text = item.post

            if (item.email.isNotEmpty()) {
                email.text = item.email
                email.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + item.email))
                    it.context.startActivity(intent)
                }
            } else {
                email.visibility = View.GONE
            }

            if (item.contact.isNotEmpty()) {
                contact.text = item.contact
                contact.setOnClickListener {
                    it.context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.contact)))
                }
            } else {
                contact.visibility = View.GONE
            }

            Glide.with(view).load(item.imageResId).into(memberImageView)
        }
    }
}
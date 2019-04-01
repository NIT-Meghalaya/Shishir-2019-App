package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import kotlinx.android.synthetic.main.sponsor_heading_item.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.SponsorItem

/**
 * Created by Devansh on 1/4/19.
 */
class SponsorListHeadingViewHolder(view: View, override var type: Int): SponsorItemViewHolder(view) {

    override fun bind(item: Any) {
        item as SponsorItem

        view.title.text = item.name
    }
}
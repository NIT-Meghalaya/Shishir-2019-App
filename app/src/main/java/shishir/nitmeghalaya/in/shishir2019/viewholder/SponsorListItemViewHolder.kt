package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.sponsor_item.view.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.SponsorItem
import shishir.nitmeghalaya.`in`.shishir2019.util.getImageResource

class SponsorListItemViewHolder(view: View): BaseViewHolder(view) {

    private lateinit var item: SponsorItem

    override fun bind(item: Any) {
        item as SponsorItem

//        if (item.imageUrl.isEmpty())
//            item.imageUrl = "krigg"

        view.apply {
//            sponsorTitle.text = item.name

            val options = RequestOptions().placeholder(R.drawable.loading)
            Glide.with(this).load(item.imageUrl).apply(options).into(sponsorImage)

        }
    }
}
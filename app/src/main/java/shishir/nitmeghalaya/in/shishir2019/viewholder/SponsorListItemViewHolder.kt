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
    private var imageResId = 0

    override fun bind(item: Any) {
        item as SponsorItem

        if (item.image.isEmpty())
            item.image = "krigg"

        imageResId = getImageResource(view.context, item.image)

        view.apply {
            sponsorTitle.text = item.name

            val options = RequestOptions().placeholder(R.drawable.krigg)
            Glide.with(this).load(getImageResource(context, item.image)).apply(options).into(sponsorImage)

//            setOnClickListener {
//            }
        }
    }
}
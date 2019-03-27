package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.event_detail_content.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.util.*

/**
 * Created by Devansh on 26/3/19.
 */
class EventDetailPagerAdapter(private val event: ShishirEvent) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val context = container.context

        val layout = LayoutInflater.from(context)
                .inflate(R.layout.event_detail_content, container, false)

        layout.apply {
            heading.text = getPageTitle(position)
            if (position == 3) {
                detailsView.visibility = View.GONE
                teamRecyclerView.adapter = EventDetailsTeamMembersAdapter(context, event.teamMembers)
                teamRecyclerView.layoutManager = LinearLayoutManager(context)
            } else {
                body.text = fromHtml(when(position) {
                    0 -> event.description
                    1 -> event.rules
                    2 -> event.judging
                    else -> ""
                })
            }
        }

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = 4

    override fun getPageTitle(position: Int) =
            when(position) {
                0 ->  DESCRIPTION
                1 -> RULES
                2 -> JUDGING
                3 -> TEAM
                else -> DESCRIPTION
            }
}
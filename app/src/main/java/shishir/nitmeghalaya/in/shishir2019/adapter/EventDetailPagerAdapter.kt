package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_team_list.view.*
import kotlinx.android.synthetic.main.event_detail_content.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.ShishirEvent
import shishir.nitmeghalaya.`in`.shishir2019.util.DESCRIPTION
import shishir.nitmeghalaya.`in`.shishir2019.util.JUDGING
import shishir.nitmeghalaya.`in`.shishir2019.util.RULES
import shishir.nitmeghalaya.`in`.shishir2019.util.TEAM
import android.text.Html
import android.os.Build
import android.text.Spanned
import shishir.nitmeghalaya.`in`.shishir2019.R


/**
 * Created by Devansh on 26/3/19.
 */
class EventDetailPagerAdapter(private val event: ShishirEvent) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val context = container.context

        val view = LayoutInflater.from(context)
                .inflate(R.layout.event_detail_content, container, false)

        view.apply {
            heading.text = getPageTitle(position)
            teamRecyclerView.layoutManager = LinearLayoutManager(context)
            if (position == 3) {
                detailsView.visibility = View.GONE
                recyclerViewTeam.adapter = EventDetailsTeamMemebersAdapter(event.teamMembers)
            } else {
                body.text = fromHtml(when(position) {
                    0 -> event.description
                    1 -> event.rules
                    2 -> event.judging
                    else -> ""
                })
            }
        }

        return view
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

    private fun fromHtml(source: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }
}
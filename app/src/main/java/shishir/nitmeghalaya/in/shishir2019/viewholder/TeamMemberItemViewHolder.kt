package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_team_member.view.*
import shishir.nitmeghalaya.`in`.shishir2019.models.TeamMember
import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView



/**
 * Created by Devansh on 26/3/19.
 */
class TeamMemberItemViewHolder(view: View): BaseViewHolder(view) {

    override fun bind(item: Any) {
        item as TeamMember

        view.apply {
            name.text = item.name
            animateTextView(name, view.context)

            if (item.team.isNotEmpty())
                post.text = "${item.post} (${item.team})"
            else
                post.text = item.post

            email.text = item.email
            animateTextView(email, view.context)

            contact.text = item.contact
            Glide.with(view).load(item.imageResId).into(memberImageView)
        }
    }

    private fun animateTextView(mTextView: TextView, context: Context) {
        val textWidth = getTextViewWidth(mTextView)
        val displayWidth = getDisplayWidth(context)

        /* Start animation only when text is longer than dislay width. */
        if (displayWidth <= textWidth) {
            val mAnimation = TranslateAnimation(
                    0f, (-textWidth).toFloat(),
                    0f, 0f)
            mAnimation.duration = 10000    // Set custom duration.
            mAnimation.startOffset = 1000    // Set custom offset.
            mAnimation.repeatMode = Animation.RESTART    // This will animate text back ater it reaches end.
            mAnimation.repeatCount = Animation.INFINITE    // Infinite animation.

            mTextView.startAnimation(mAnimation)
        }
    }

    private fun getDisplayWidth(context: Context): Int {
        val displayWidth: Int

        val windowManager = context.getSystemService(
                Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val screenSize = Point()

        display.getSize(screenSize)
        displayWidth = screenSize.x

        return displayWidth
    }

    private fun getTextViewWidth(textView: TextView): Int {
        textView.measure(0, 0)    // Need to set measure to (0, 0).
        return textView.measuredWidth
    }
}
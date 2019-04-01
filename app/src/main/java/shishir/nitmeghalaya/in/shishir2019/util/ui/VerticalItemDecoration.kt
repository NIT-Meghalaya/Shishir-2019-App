package shishir.nitmeghalaya.`in`.shishir2019.util.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R

/**
 * Created by Devansh on 1/4/19.
 */

class VerticalItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position

        if (position == 0)
            outRect.top = view.context.resources.getDimension(R.dimen.actionBarSize).toInt()
    }
}
package shishir.nitmeghalaya.`in`.shishir2019.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Devansh on 24/3/19.
 */
abstract class BaseViewHolder(protected val view: View): RecyclerView.ViewHolder(view) {

    abstract fun bind(item: Any)
}
package shishir.nitmeghalaya.`in`.shishir2019.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Devansh on 3/3/19.
 */

public fun Toast.makeShort(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
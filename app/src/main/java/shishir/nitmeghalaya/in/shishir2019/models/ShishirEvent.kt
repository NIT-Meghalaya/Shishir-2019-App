package shishir.nitmeghalaya.`in`.shishir2019.models

import android.graphics.drawable.GradientDrawable

data class ShishirEvent (
    val name: String = "",
    val description: String = "",
    val rules: String = "",
    val judging: String = "",
    var image: String = "",
    var foregroundGradient: GradientDrawable? = null,
    val subEvents: List<ShishirEvent> = listOf()
)

package shishir.nitmeghalaya.`in`.shishir2019.models

import android.graphics.drawable.GradientDrawable

data class ShishirEvent (
    val name: String = "",
    val description: String = "",
    val rules: String = "",
    val judging: String = "",
    var image: String = "",
    @Transient var foregroundGradient: GradientDrawable? = null,
    var titleTextColor: Int = 0,
    val subEvents: List<ShishirEvent> = listOf()
)

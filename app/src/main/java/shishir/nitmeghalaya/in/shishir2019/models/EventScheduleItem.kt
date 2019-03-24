package shishir.nitmeghalaya.`in`.shishir2019.models

/**
 * Created by Devansh on 10/3/19.
 */

data class EventScheduleItem(
    val name: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val date: String = "",
    val image: String = "",
    var dominantColor: Int = 0,
    var imageResId: Int = 0
)
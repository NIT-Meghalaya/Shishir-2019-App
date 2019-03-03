package shishir.nitmeghalaya.`in`.shishir2019.models

data class ShishirEvent (
    val name: String = "",
    val description: String = "",
    val rules: String = "",
    val judging: String = "",
    val subEvents: List<ShishirEvent>
)

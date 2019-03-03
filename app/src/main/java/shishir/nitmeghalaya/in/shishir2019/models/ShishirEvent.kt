package shishir.nitmeghalaya.`in`.shishir2019.models

data class ShishirEvent (
    val name: String = "",
    val description: String = "",
    val rules: String = "",
    val judging: String = "",
    var image: String = "",
    val subEvents: List<ShishirEvent> = listOf()
)

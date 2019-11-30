package comers_0890.httpsvk.zhssb.data.ResponseError

import java.util.*

data class ResponseError(
    val timestamp: Date?,
    val status: Int?,
    val error: String?,
    val exception: String?,
    val message: String?,
    val path: String?,
    val supported: Boolean?,

    val code: String?,
    val url: String?,
    val errors: ArrayList<String>?
)
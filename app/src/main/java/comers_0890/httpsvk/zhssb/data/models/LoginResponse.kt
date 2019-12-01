package comers_0890.httpsvk.zhssb.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("status")
    val status: String
)
package uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Gravatar(
    @SerializedName("hash")
    val hash: String // d33c06f0a87b905bc466411094384c97
)
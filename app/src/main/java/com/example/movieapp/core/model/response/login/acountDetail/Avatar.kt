package uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Avatar(
    @SerializedName("gravatar")
    val gravatar: Gravatar,
    @SerializedName("tmdb")
    val tmdb: Tmdb
)
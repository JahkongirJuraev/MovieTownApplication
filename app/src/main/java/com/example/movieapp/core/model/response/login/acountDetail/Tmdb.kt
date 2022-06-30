package uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: String // /AcdbqK5PPIJGwEM1yLlKaNXSuhm.jpg
)
package uz.fozilbekimomov.lesson44movieapp.core.models.response.login.acountDetail


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AccountDetailResponse(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int, // 9328509
    @SerializedName("iso_639_1")
    val iso6391: String, // en
    @SerializedName("iso_3166_1")
    val iso31661: String, // US
    @SerializedName("name")
    val name: String, // Fozilbek Imomov
    @SerializedName("include_adult")
    val includeAdult: Boolean, // false
    @SerializedName("username")
    val username: String // AndroGame
)
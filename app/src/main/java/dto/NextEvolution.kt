package dto


import com.google.gson.annotations.SerializedName

data class NextEvolution(
    @SerializedName("name")
    val name: String,
    @SerializedName("num")
    val num: String
)
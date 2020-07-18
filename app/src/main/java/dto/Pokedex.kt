package dto


import com.google.gson.annotations.SerializedName

data class Pokedex(
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)
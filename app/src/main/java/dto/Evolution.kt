package dto

import com.google.gson.annotations.SerializedName

class Evolution {

    @SerializedName("num")
    var evolutionNumber : String? = null
    @SerializedName("name")
    var pokemonEvolutionName : String? = null
}
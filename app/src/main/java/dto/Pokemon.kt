package dto

import com.google.gson.annotations.SerializedName

class Pokemon {

    @SerializedName("id")
    var pokemonId = 0
    @SerializedName("name")
    var pokemonName : String? = null
    @SerializedName("img")
    var pokemonImg : String? = null
    @SerializedName("type")

    override fun toString(): String {
        return "$pokemonName"
    }
}
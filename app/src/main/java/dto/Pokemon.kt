package dto

import com.google.gson.annotations.SerializedName

class Pokemon {

    @SerializedName("id")
    var pokemonId = 0
    @SerializedName("num")
    var pokemonNum : String? = null
    @SerializedName("name")
    var pokemonName : String? = null
    @SerializedName("img")
    var pokemonImg : String? = null
    @SerializedName("type")
    var pokemonType : List<String>? = null
    @SerializedName("height")
    var pokemonHeight: String? = null
    @SerializedName("weight")
    var pokemonWeight : String? = null
    @SerializedName("candy")
    var pokemonCandy : String? = null
    @SerializedName("egg")
    var pokemonEgg : String? = null
    @SerializedName("spawn_choice")
    var pokemonSpawnChoice = 0.toDouble()
    @SerializedName("avg_spawns")
    var pokemonAvgSpawns = 0.toDouble()
    @SerializedName("spawn_time")
    var pokemonSpawnTime : String? = null
    @SerializedName("multipliers")
    var pokemonMultipliers : List<Double>? = null
    @SerializedName("weakness")
    var pokemonWeakness : List<String>? = null
    @SerializedName("next_evolution")
    var pokemonNextEvolution : List<Evolution>? = null
    @SerializedName("prev_evolution")
    var pokemonPrevEvolution : List<Evolution>? = null

    override fun toString(): String {
        return "$pokemonName"
    }
}
package dto

data class Locations(var pokemonName: String = "", var pokemonType: String = "", var pokemonDescription: String = "") {
    override fun toString(): String {
        return "$pokemonName $pokemonType $pokemonDescription"
    }
}
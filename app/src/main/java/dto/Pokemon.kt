package dto

data class Pokemon(var pokemonId: Int = 0, var name: String="", var description: String="", var types: ArrayList<String>) {

    override fun toString() = "$name $description"
}
package dao
import dto.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IPokemonDAO {
    @GET(value = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json")
    fun getAllPokemon(): Call<ArrayList<Pokemon>>

    @GET(value = "https://pokeapi.co/api/v2/pokemon/")
    fun getPokemon(@Query("Combined_Name")pokemonName: String): Call<ArrayList<Pokemon>>
}
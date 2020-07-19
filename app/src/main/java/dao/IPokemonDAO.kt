package dao

import dto.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonDAO {

    @get:GET("pokedex.json")
    val pokemonList : Observable<Pokedex>
}
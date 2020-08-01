package edu.uc.it3048.mypokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dto.Pokemon
import edu.uc.it3048.mypokedex.ui.main.MainViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PokemonDataUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule();

    @Before
    fun populatePokemon() {
        var mvm = MainViewModel()
    }

    private fun createMockData(): ArrayList<Pokemon> {
        var pokemonList = ArrayList<Pokemon>();
        var charmander : Pokemon = Pokemon();
            charmander.pokemonId = 4;
            charmander.pokemonName = "Charmander";
        pokemonList.add(charmander)

        var squirtle : Pokemon = Pokemon();
            squirtle.pokemonId = 7;
            squirtle.pokemonName = "Squirtle"
        pokemonList.add(squirtle)

        var bulbasaur : Pokemon = Pokemon();
            bulbasaur.pokemonId = 1;
            bulbasaur.pokemonName = "Bulbasaur";
        pokemonList.add(bulbasaur)

        return pokemonList;
    }

    @Test
    fun confirmCharmander_outputsCharmander(){
        var pokemon: Pokemon = Pokemon();
        pokemon.pokemonId = 4;
        pokemon.pokemonName = "Charmander";
        assertEquals("Charmander", pokemon.toString());
    }

    fun givenAFeedOfData(): ArrayList<Pokemon> {
        var data = createMockData();
        return data;
    }

    fun searchForSquirtle(data : ArrayList<Pokemon>): String {
        var charmander = data.get(1).pokemonName.toString();
        return charmander;
    }

    @Test
    fun searchReturnsCharmander(){
        var data = givenAFeedOfData();
        var result = searchForSquirtle(data);
        assertEquals("Squirtle", result);
    }
}
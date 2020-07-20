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

    @Test
    fun pokemonDTO_maintainsState(){
        var pokemon = Pokemon();

    }

}
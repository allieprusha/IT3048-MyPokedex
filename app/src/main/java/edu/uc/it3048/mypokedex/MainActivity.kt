package edu.uc.it3048.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import dao.IPokemonDAO
import dto.Pokemon
import dto.PokemonViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    private val retrofit = RetrofitClientInstance.retrofitInstance
    private var iPokemonList = retrofit?.create(IPokemonDAO::class.java)
    var pokemonList : List<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        rcycViewPokemon.layoutManager = LinearLayoutManager(this)

        // Method calling
        login()
        fetchPokemonData()
        populatePokemonAutoComplete()
    }

    // Method to login to firebase, proceed to new screen
    private fun login(){
        val loginButton = findViewById<ImageButton>(R.id.btnLogin)
            loginButton.setOnClickListener {
                val loginIntent = Intent(this, ProfileScreenActivity::class.java)
                startActivity(loginIntent)
            }
    }

    private fun fetchPokemonData() {
        compositeDisposable.add(iPokemonList!!.pokemonList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemon ->
                pokemonList = pokemon.pokemon!!

                val pokemonViewAdapter= PokemonViewAdapter(this, pokemonList)
                rcycViewPokemon.adapter = pokemonViewAdapter
            })
    }

    private fun populatePokemonAutoComplete() {
        compositeDisposable.add(iPokemonList!!.pokemonList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemon ->
                pokemonList = pokemon.pokemon!!

                val autoCompleteAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, pokemonList)
                atcPokemonSearch.setAdapter(autoCompleteAdapter)
            })
    }

    private fun filterPokemonRecyclerView() {
        // TODO: Filter the pokemon list based off of search text
    }
}

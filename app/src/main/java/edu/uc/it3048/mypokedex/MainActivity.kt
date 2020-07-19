package edu.uc.it3048.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    internal var compositeDisposable = CompositeDisposable()
    private val retrofit = RetrofitClientInstance.retrofitInstance
    internal var iPokemonList = retrofit?.create(IPokemonDAO::class.java)
    var pokemonList : List<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        rcycViewPokemon.layoutManager = LinearLayoutManager(this)

        // Calling the login method
        login()
        fetchPokemonData()
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

                val adapter = PokemonViewAdapter(this, pokemonList)
                rcycViewPokemon.adapter = adapter
            })
    }
}

package edu.uc.it3048.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
    var pokemonList : MutableList<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        rcycViewPokemon.layoutManager = LinearLayoutManager(this)

        // Method calling
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
                pokemonList = pokemon.pokemon!! as MutableList<Pokemon>

                val newPokemonList = ArrayList<String>()
                newPokemonList.add(0, "All Pokemon")
                for (pokemonName in pokemonList) {
                    newPokemonList.add(pokemonName.pokemonName!!)
                }


                val pokemonViewAdapter = PokemonViewAdapter(this, pokemonList)
                rcycViewPokemon.adapter = pokemonViewAdapter

                val autoCompleteAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, newPokemonList)
                pokemonSpinner.adapter = autoCompleteAdapter

                pokemonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) { }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        if (autoCompleteAdapter.getItem(position) == "All Pokemon") {
                            Log.d("AutoComplete", "All Pokemon Called")
                        } else {

                            val pokemonInfoIntent = Intent(view?.context, PokemonInfoActivity::class.java)
                            pokemonInfoIntent.putExtra("image", pokemonList[position - 1].pokemonImg)
                            pokemonInfoIntent.putExtra("id", pokemonList[position - 1].pokemonId.toString())
                            pokemonInfoIntent.putExtra("num", pokemonList[position - 1].pokemonNum.toString())
                            pokemonInfoIntent.putExtra("type", pokemonList[position - 1].pokemonType.toString())
                            pokemonInfoIntent.putExtra("candy", pokemonList[position - 1].pokemonCandy)
                            pokemonInfoIntent.putExtra("egg", pokemonList[position - 1].pokemonEgg)
                            pokemonInfoIntent.putExtra("spawn_choice", pokemonList[position - 1].pokemonSpawnChoice.toString())
                            pokemonInfoIntent.putExtra("avg_spawns", pokemonList[position - 1].pokemonAvgSpawns.toString())
                            pokemonInfoIntent.putExtra("spawn_time", pokemonList[position - 1].pokemonSpawnTime)
                            view?.context?.startActivity(pokemonInfoIntent)
                        }

                    }
                }
            })
    }
}

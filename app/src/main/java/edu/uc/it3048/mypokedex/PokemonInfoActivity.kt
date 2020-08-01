package edu.uc.it3048.mypokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_info_activity.*

class PokemonInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_info_activity)

        // Allows for back arrow navigation
        val actionBar = supportActionBar
        actionBar!!.title = "Saved Locations"
        actionBar.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("image")
            && intent.hasExtra("id")
            && intent.hasExtra("num")
            && intent.hasExtra("type")
            && intent.hasExtra("candy")
            && intent.hasExtra("egg")
            && intent.hasExtra("spawn_choice")
            && intent.hasExtra("avg_spawns")
            && intent.hasExtra("spawn_time")) {

            val pokemonImage = intent.getStringExtra("image")
            val pokemonId = intent.getStringExtra("id")
            val pokemonNum = intent.getStringExtra("num")
            val pokemonType = intent.getStringExtra("type")
            val pokemonCandy = intent.getStringExtra("candy")
            val pokemonEgg = intent.getStringExtra("egg")
            val pokemonSpawnChoice = intent.getStringExtra("spawn_choice")
            val pokemonAvgSpawns = intent.getStringExtra("avg_spawns")
            val pokemonSpawnTime = intent.getStringExtra("spawn_time")

            Glide.with(this).load(pokemonImage).override(500, 500).into(pokemonInfoImage)
            lblID.text = pokemonId
            lblPokemonNum.text = pokemonNum
            lblPokemonType.text = pokemonType
            lblCandy.text = pokemonCandy
            lblEgg.text = pokemonEgg
            lblSpawnChoice.text = pokemonSpawnChoice
            lblAvgSpawns.text = pokemonAvgSpawns
            lblSpawnTime.text = pokemonSpawnTime
        }
    }

    // Press the back button to go back to previous activity
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }
}
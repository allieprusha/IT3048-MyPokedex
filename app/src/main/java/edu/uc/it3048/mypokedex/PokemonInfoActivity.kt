package edu.uc.it3048.mypokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_info_activity.*

class PokemonInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_info_activity)

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        Log.d("Intent", "getIncomingIntent: Checking for incoming intents.")

        if (intent.hasExtra("image") && intent.hasExtra("id")) {
            val pokemonImage = intent.getStringExtra("image")
            val pokemonId = intent.getStringExtra("id")

           lblID.text = pokemonId
            Glide.with(this).load(pokemonImage).override(500, 500).into(pokemonInfoImage)
        }
    }
}
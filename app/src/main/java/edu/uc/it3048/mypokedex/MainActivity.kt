package edu.uc.it3048.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import dto.PokemonViewAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Calling the login method
        login()

        rcycViewPokemon.layoutManager = LinearLayoutManager(this)
        rcycViewPokemon.adapter = PokemonViewAdapter()
    }

    // Method to login to firebase, proceed to new screen
    private fun login(){
        val loginButton = findViewById<ImageButton>(R.id.btnLogin)
            loginButton.setOnClickListener {
                val loginIntent = Intent(this, ProfileScreenActivity::class.java)
                startActivity(loginIntent)
            }
    }
}

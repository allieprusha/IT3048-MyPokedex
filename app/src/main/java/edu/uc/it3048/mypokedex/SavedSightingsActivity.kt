package edu.uc.it3048.mypokedex

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dto.LocationAdapter
import kotlinx.android.synthetic.main.saved_sightings_activity.*

class SavedSightingsActivity : AppCompatActivity() {


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_sightings_activity)

        // Allows for back arrow navigation
        val actionBar = supportActionBar
        actionBar!!.title = "Saved Locations"
        actionBar.setDisplayHomeAsUpEnabled(true)

        recViewLocations.layoutManager = LinearLayoutManager(this)
        recViewLocations.adapter = LocationAdapter()
    }

    // Press the back button to go back to previous activity
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }
}

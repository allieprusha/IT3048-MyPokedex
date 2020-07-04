package edu.uc.it3048.mypokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class SavedSightingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_sightings_activity)

        // Allows for back arrow navigation
        val actionBar = supportActionBar
        actionBar!!.title = "Saved Locations"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    // Press the back button to go back to previous activity
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

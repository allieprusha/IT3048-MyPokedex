package edu.uc.it3048.mypokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ProfileScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen_activity)

        // Calling the savedLocations method
        savedLocations()
    }

    // Method to view saved pokemon sighting locations
    private fun savedLocations(){
        val folderButton = findViewById<ImageButton>(R.id.imgBtnFolder)
            folderButton.setOnClickListener {
                val savedLocationsIntent = Intent(this, SavedSightingsActivity::class.java)
                startActivity(savedLocationsIntent)
            }
    }
}

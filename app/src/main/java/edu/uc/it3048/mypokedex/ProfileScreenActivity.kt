package edu.uc.it3048.mypokedex

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.profile_screen_activity.*
import java.util.jar.Manifest

class ProfileScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen_activity)

        // Calling the savedLocations and openCamera method
        savedLocations()
        takePhoto()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Populates the image view with the picture taken
        if (requestCode == 123){
            val map = data?.extras?.get("data") as Bitmap
            imgPokemonLocation.setImageBitmap(map)
        }
    }

    // Method to open camera and take photo
    private fun takePhoto(){
        btnCamera.setOnClickListener {
            val imageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageIntent, 123)
        }
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

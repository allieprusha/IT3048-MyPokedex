package edu.uc.it3048.mypokedex

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.profile_screen_activity.*
import java.util.jar.Manifest

class ProfileScreenActivity : AppCompatActivity() {

    private val LOGIN_REQUEST_CODE: Int = 607
    lateinit var loginProviders : List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen_activity)

        // Lists the login providers that we are using
        loginProviders = listOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Calling various methods
        savedLocations()
        takePhoto()
        showSignInOptions()
        onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // User must login with email or else an error toast message appears
        if (requestCode == LOGIN_REQUEST_CODE){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK){
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "" + user!!.email, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "" + response!!.error!!.message, Toast.LENGTH_SHORT).show()
            }
        }

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

    // Shows list of login options (Email and Google)
    private fun showSignInOptions(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(loginProviders)
            .setTheme(R.style.AppTheme).build(), LOGIN_REQUEST_CODE)
    }
}

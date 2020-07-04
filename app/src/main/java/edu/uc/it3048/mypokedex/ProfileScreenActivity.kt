package edu.uc.it3048.mypokedex

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import app.plantdiary.individualassignment3048q.dto.Locations
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.profile_screen_activity.*

class ProfileScreenActivity : AppCompatActivity() {

    private val LOGIN_REQUEST_CODE: Int = 607
    private lateinit var loginProviders : List<AuthUI.IdpConfig>
    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen_activity)

        // Lists the login providers that we are using
        loginProviders = listOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Calling various methods
        savedLocationsFolder()
        takePhoto()
        showSignInOptions()
        onSupportNavigateUp()
        btnSaveLocation()
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
    private fun savedLocationsFolder(){
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

    // Button for saving locations to Firebase
    private fun btnSaveLocation(){
        val save = findViewById<ImageButton>(R.id.imgBtnSave)
            save.setOnClickListener {
                saveLocation()
            }
    }

    private fun saveLocation(){
        val sightingLocation = Locations().apply {
            pokemonName = edtTextPokemonName.text.toString()
            pokemonType = edtTextPokemonType.text.toString()
            pokemonDescription = edtTxtPokemonDescription.text.toString()
        }

        save(sightingLocation)
    }

    // Populates database with data from profile screen text fields
    private fun save(location: Locations){
        firestore.collection("locations")
            .document()
            .set(location)

            // TODO add log statement?
    }
}

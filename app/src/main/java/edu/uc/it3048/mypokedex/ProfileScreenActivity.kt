package edu.uc.it3048.mypokedex

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import dto.Locations
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.profile_screen_activity.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ProfileScreenActivity : AppCompatActivity() {
    private val LOGIN_REQUEST_CODE: Int = 607
    private val SAVE_IMAGE_REQUEST_CODE = 513
    private val CAMERA_REQUEST_CODE = 918
    private val GALLERY_REQUEST_CODE = 555
    private lateinit var loginProviders : List<AuthUI.IdpConfig>
    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var currentPhotoPath : String
    private var selectedPhotoUri : Uri? = null

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen_activity)

        // Lists the login providers that we are using
        loginProviders = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Calling various methods
        savedLocationsFolder()
        showSignInOptions()
        onSupportNavigateUp()
        btnSaveLocation()

        btnCamera.setOnClickListener {
            prepTakePhoto()
        }

        imgBtnGallery.setOnClickListener {
            prepOpenGallery()
        }
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
        } else if (requestCode == SAVE_IMAGE_REQUEST_CODE) {
            Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show()
        } else if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null && data.data != null) {
                selectedPhotoUri = data.data
                val source = ImageDecoder.createSource(this.contentResolver, selectedPhotoUri!!)
                val bitmap = ImageDecoder.decodeBitmap(source)
                imgPokemonLocation.setImageBitmap(bitmap)
            }
        }
    }

    // Method to open camera and take photo
    private fun takePhoto(){
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                takePictureIntent -> takePictureIntent.resolveActivity(this.packageManager)
                if (takePictureIntent == null) {
                    Toast.makeText(this, "Unable to save photo", Toast.LENGTH_LONG).show()
                } else {
                    val photoFile = createImageFile()
                    photoFile.also {
                        var photoURI = FileProvider.getUriForFile(this, "edu.uc.it3048.mypokedex", it)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)
                        startActivityForResult(takePictureIntent, SAVE_IMAGE_REQUEST_CODE)
                    }
                }
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
                uploadImageToFirebaseStorage()
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

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile() : File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss".format(Date()))
        val storageDir : File? = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("MyPokedex${timeStamp}", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun prepTakePhoto() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            takePhoto()
        } else {
            val permissionRequest = arrayOf(android.Manifest.permission.CAMERA)
            requestPermissions(permissionRequest, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto()
                } else {
                    Toast.makeText(this, "Unable to take photo without permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun prepOpenGallery() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            startActivityForResult(this, GALLERY_REQUEST_CODE)
        }
    }

    private fun uploadImageToFirebaseStorage() {
        val fileName = UUID.randomUUID().toString()
        val storageReference = FirebaseStorage.getInstance().getReference("/images/$fileName")

        storageReference.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Toast.makeText(this, "Upload successful", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
    }
}

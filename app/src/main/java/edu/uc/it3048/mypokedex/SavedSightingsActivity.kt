package edu.uc.it3048.mypokedex

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dto.Locations
import dto.Photo
import kotlinx.android.synthetic.main.location_row.view.*
import kotlinx.android.synthetic.main.profile_screen_activity.*
import kotlinx.android.synthetic.main.saved_sightings_activity.*

class SavedSightingsActivity : AppCompatActivity() {

    private val firebaseReference = FirebaseFirestore.getInstance()
    private val query = firebaseReference.collection("locations").orderBy("pokemonName", Query.Direction.ASCENDING)
    private val options = FirestoreRecyclerOptions.Builder<Locations>().setQuery(query, Locations::class.java).build()
    private var adapter: LocationsFirestoreRecyclerAdapter? = null
    private val storage = Firebase.storage

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_sightings_activity)

        // Allows for back arrow navigation
        val actionBar = supportActionBar
        actionBar!!.title = "Saved Locations"
        actionBar.setDisplayHomeAsUpEnabled(true)

        recViewLocations.layoutManager = LinearLayoutManager(this)
        //recViewLocations.adapter = LocationAdapter()

        adapter = LocationsFirestoreRecyclerAdapter(options)
        recViewLocations.adapter = adapter
    }

    // Press the back button to go back to previous activity
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

    // Sets up using FirestoreRecyclerAdapter
    private inner class LocationsViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view) {
        internal fun setPokemonName(pokemonName: String) {
            val txtPokemonName = view.findViewById<TextView>(R.id.txtName)
            txtPokemonName.text = pokemonName
        }

        internal fun setPokemonDescription(pokemonDescription: String) {
            val txtPokemonDescription = view.findViewById<TextView>(R.id.txtDescription)
            txtPokemonDescription.text = pokemonDescription
        }

        internal fun setPokemonType(pokemonType: String) {
            val txtPokemonType = view.findViewById<TextView>(R.id.txtType)
            txtPokemonType.text = pokemonType
        }

        internal fun setLocationImage(locationId : String){
            val imgLocationRecycler = view.findViewById<ImageView>(R.id.imgLocationRecycler)
            var photoUri: String
            val queryPhoto = firebaseReference.collection("locations").document(locationId).collection("photos")
            queryPhoto.get()
                .addOnSuccessListener { collection ->
                    if (collection != null && collection.any()){
                        var photoDTO = collection.firstOrNull()?.toObject<Photo>() ?: Photo()
                        photoUri = photoDTO?.remoteUri
                        val httpsReference = storage.getReferenceFromUrl(photoUri)
                        val ONE_GIGABYTE: Long = 1024 * 1024 * 1024
                        var bitmap = httpsReference.getBytes(ONE_GIGABYTE)
                            .addOnSuccessListener {
                                imgLocationRecycler.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(it, 0, it.size), 250, 250, true))
                            }
                    }
                }
                .addOnFailureListener {
                    Log.e("Firebase", it.message)
                }
        }
    }

    // Sets up using FirestoreRecyclerAdapter
    private inner class LocationsFirestoreRecyclerAdapter internal constructor(options: FirestoreRecyclerOptions<Locations>) : FirestoreRecyclerAdapter<Locations, LocationsViewHolder>(options) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.location_row, parent, false)
            return LocationsViewHolder(view)
        }

        override fun onBindViewHolder(holder: LocationsViewHolder, position: Int, model: Locations) {
            holder.setPokemonName(model.pokemonName)
            holder.setPokemonDescription(model.pokemonDescription)
            holder.setPokemonType(model.pokemonType)
            holder.setLocationImage(model.locationId)
        }

    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()

        if (adapter != null) {
            adapter!!.stopListening()
        }
    }
}

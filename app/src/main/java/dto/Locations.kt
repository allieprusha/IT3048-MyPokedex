package dto

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

data class Locations(var pokemonName: String = "",
                     var pokemonType: String = "",
                     var pokemonDescription: String = ""
)
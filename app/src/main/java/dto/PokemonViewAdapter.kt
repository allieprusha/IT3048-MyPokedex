package dto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uc.it3048.mypokedex.R
import kotlinx.android.synthetic.main.location_row.view.*
import kotlinx.android.synthetic.main.pokemon_row.view.*

class PokemonViewAdapter : RecyclerView.Adapter<CustomViewHolderV2>() {

    val pokemonName = listOf("Balbasaur", "Charmander", "Squirtle")

    override fun getItemCount(): Int {
        return pokemonName.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderV2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.pokemon_row, parent, false)
        return CustomViewHolderV2(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderV2, position: Int) {

        holder.view.txtRecyclerPokemonName.text = pokemonName[position]
    }
}

class CustomViewHolderV2(val view: View) : RecyclerView.ViewHolder(view) {

}
package dto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.uc.it3048.mypokedex.R

class PokemonViewAdapter(private var context: Context, private var pokemonList : List<Pokemon>) : RecyclerView.Adapter<PokemonViewAdapter.CustomViewHolderV2>() {

    inner class CustomViewHolderV2(view: View) : RecyclerView.ViewHolder(view) {
        internal var pokemonImage = itemView.findViewById<ImageView>(R.id.imgPokemon)
        internal var pokemonName = itemView.findViewById<TextView>(R.id.txtRecyclerPokemonName)
        internal var pokemonId = itemView.findViewById<TextView>(R.id.txtRecyclerPokemonId)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderV2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.pokemon_row, parent, false)
        return CustomViewHolderV2(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderV2, position: Int) {
        Glide.with(context).load(pokemonList[position].pokemonImg).override(500, 500).into(holder.pokemonImage)
        holder.pokemonName.text = pokemonList[position].pokemonName
        holder.pokemonId.text = pokemonList[position].pokemonId.toString()
    }
}
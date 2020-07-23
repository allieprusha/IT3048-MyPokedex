package dto

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.uc.it3048.mypokedex.PokemonInfoActivity
import edu.uc.it3048.mypokedex.R

class PokemonViewAdapter(private var context: Context, private var pokemonList : List<Pokemon>) : RecyclerView.Adapter<PokemonViewAdapter.CustomViewHolderV2>() {

    inner class CustomViewHolderV2(view: View) : RecyclerView.ViewHolder(view) {
        var pokemonImage: ImageView = itemView.findViewById(R.id.imgPokemon)
        var pokemonName: TextView = itemView.findViewById(R.id.txtRecyclerPokemonName)
        var pokemonHeight: TextView = itemView.findViewById(R.id.txtRecyclerPokemonId)
        var pokemonWeight: TextView = itemView.findViewById(R.id.txtWeight)
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
        holder.pokemonHeight.text = pokemonList[position].pokemonHeight.toString()
        holder.pokemonWeight.text = pokemonList[position].pokemonWeight.toString()

        holder.itemView.setOnClickListener {
            Toast.makeText(context, pokemonList[position].pokemonName, Toast.LENGTH_LONG).show()

            val pokemonInfoIntent = Intent(context, PokemonInfoActivity::class.java)
            pokemonInfoIntent.putExtra("image", pokemonList[position].pokemonImg)
            pokemonInfoIntent.putExtra("id", pokemonList[position].pokemonId.toString())
            context.startActivity(pokemonInfoIntent)
        }
    }
}


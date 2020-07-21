package dto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uc.it3048.mypokedex.R
import kotlinx.android.synthetic.main.location_row.view.*

class LocationAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val pokemonName = listOf("Bulbasaur", "Charmander", "Squirtle")

    override fun getItemCount(): Int {
        return pokemonName.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.location_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.view.txtName.text = pokemonName[position]
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}

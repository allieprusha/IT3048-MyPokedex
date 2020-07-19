package dto


import com.google.gson.annotations.SerializedName

class Pokemon {

    var id = 0
    var num : String? = null
    var name : String? = null
    var img : String? = null
    var type : List<String>? = null
    var height: String? = null
    var weight : String? = null
    var candy : String? = null
    var egg : String? = null
    var spawn_choice = 0.toDouble()
    var avg_spawns = 0.toDouble()
    var spawn_time : String? = null
    var multipliers : List<Double>? = null
    var weakness : List<String>? = null
    var next_evolution : List<Evolution>? = null
    var prev_evolution : List<Evolution>? = null
}
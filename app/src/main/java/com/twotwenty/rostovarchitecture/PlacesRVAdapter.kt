package com.twotwenty.rostovarchitecture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PlacesRVAdapter(var options: ArrayList<PlaceModel>, var context: Context): RecyclerView.Adapter<PlacesRVAdapter.RVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.poi_element, parent, false)

        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val place: PlaceModel = options[position]
        holder.name.text = place.Name
        holder.description.text = place.Description
        holder.place_name.text = place.Adress
        Picasso.get().load(place.img).fit().into(holder.image)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onViewAttachedToWindow(holder: RVViewHolder) {
        super.onViewAttachedToWindow(holder)
        var model = options[holder.adapterPosition]
        var modelToSerialize = SerializeAdapter(model.Name, model.Description, model.img, model.Adress, model.pointr?.latitude, model.pointr?.longitude)

        holder.itemView.setOnClickListener{
            MainActivity.openActivity(modelToSerialize,context)
        }
    }

    class RVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var description: TextView
        var place_name: TextView
        var view: View
        var image: ImageView? = null
        init {
            name = itemView.findViewById(R.id.poi_preview_name)
            description = itemView.findViewById(R.id.poi_preview_description)
            place_name = itemView.findViewById(R.id.poi_preview_place_name)
            image = itemView.findViewById(R.id.poi_preview_image)
            view = itemView.findViewById(R.id.poi_preview_view)

        }


    }
}
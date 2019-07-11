package com.example.ktask.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ktask.R
import com.example.ktask.model.ModelPlaces
import kotlin.collections.ArrayList

class ApllPlaceAdapter(private var context: Context, var list:List<ModelPlaces>,var task1:TaskHandler1) :
    RecyclerView.Adapter<ApllPlaceAdapter.ViewHolderPlace>() {

    var modelPlaces1 = ModelPlaces()
    var listSave = ArrayList<ModelPlaces>()
    var pos: Int = 0
    interface TaskHandler1{fun itemClick(pos:Int,modelPlaces: ModelPlaces,status:Int)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPlace {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.all_places_item_layout, parent, false) as View

        return ViewHolderPlace(view)
    }

    override fun getItemCount(): Int {
        Log.e("list count", "" + list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderPlace, position: Int) {

        var modelPlaces = list.get(position)

        if(modelPlaces.isSaved)
            holder.imageSave.setImageResource(R.color.colorAccent)
        else holder.imageSave.setImageResource(R.color.primary_dark_material_dark)
        task1  as TaskHandler1

        this.task1=task1
        holder.placeIdText.text = modelPlaces.placeId
        holder.placeNameText.text = modelPlaces.placeName

        holder.imageSave.setOnClickListener {
            holder.imageSave.setImageResource(R.color.colorAccent)
            modelPlaces1 = list[position]
            Log.e("", "save test " + position + modelPlaces1)
            task1.itemClick(holder.adapterPosition,modelPlaces1,1)

        }
    }

   override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolderPlace(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        interface TaskPosition {fun clickPos(pos:Int)}
        lateinit var task3 : TaskPosition

        override fun onClick(v: View?) {

            var pos: Int = adapterPosition
            task3.clickPos(pos)

            imageSave.setOnClickListener(this)

        }

        var placeIdText = itemView?.findViewById<TextView>(R.id.tv_placeId)
        var placeNameText = itemView?.findViewById<TextView>(R.id.tv_placeName)
        var imageSave = itemView?.findViewById<ImageView>(R.id.img1)
    }}

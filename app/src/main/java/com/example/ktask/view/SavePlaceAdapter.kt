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
import com.example.ktask.model.PlaceAppDatabase

class SavePlaceAdapter (context: Context, private val list:ArrayList<ModelPlaces>): RecyclerView.Adapter<ViewSavePlaceHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewSavePlaceHolder {


        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.save_places_item_layout,parent,false)
        return ViewSavePlaceHolder(view)

    }

    override fun onBindViewHolder(holder: ViewSavePlaceHolder, position: Int) {

        var modelPlaces = ModelPlaces()

        Log.e("save ki list2",""+list)
        modelPlaces = list.get(position)
        //var id =
        //var nm =

        holder.idText.setText(modelPlaces.placeId )
        holder.nameText.setText(modelPlaces.placeName)

        holder.imag.setOnClickListener {
            holder.imag.setBackgroundResource(R.color.button_material_dark)
            Log.e("save ki list3",""+list)
            Thread{
                var db = PlaceAppDatabase.getDatabase(holder.nameText.context)
                var i = db?.placedao()?.deletePlace(modelPlaces)
                Log.e("deletion..",""+i)

            }.start()

        }

    }

    override fun getItemCount(): Int {
        Log.e("list save size", "" + list.size)
        return list.size

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}

class ViewSavePlaceHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
    override fun onClick(v: View?) {
        imag.setOnClickListener(this)
    }

    var idText = itemView.findViewById<TextView>(R.id.tv_id)
    var nameText = itemView.findViewById<TextView>(R.id.tv_name)
    var imag = itemView.findViewById<ImageView>(R.id.img2)

}

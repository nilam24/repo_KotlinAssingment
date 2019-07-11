package com.example.ktask.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
data class ModelPlaces(
    @field : PrimaryKey
    @SerializedName("id")
    @Expose
    var placeId: String,
    @SerializedName("name")
    @Expose
    var placeName: String,
var isSaved: Boolean

)
{   @Ignore
    constructor() : this("","", false)

    @Ignore
    constructor( placeId: String,  placeName: String) :this(placeId, placeName, false)

}

/* package com.example.ktask.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class ModelPlaces {

    @field : PrimaryKey(autoGenerate = true)
    var Id: Int = 0
    @field : ColumnInfo(name = "PlaceId")
    var placeId: String = ""
    @field : ColumnInfo(name = "PlaceName")
    var placeName: String = ""

    @Ignore
    constructor(placeId: String, placeName: String) {
        this.placeId = placeId
        this.placeName = placeName
    }

    constructor()


}*/
package com.example.ktask.viewmodel

import android.arch.lifecycle.LiveData
import android.graphics.ColorSpace
import com.example.ktask.model.ModelPlaces

interface IPlaceViewModel {
    fun insert(modelPlaces: ModelPlaces) : Long
    fun select() : LiveData<ModelPlaces>
    fun select1() : LiveData<List<ModelPlaces>>
    fun delete(modelPlaces: ModelPlaces) :Int


}
package com.example.ktask.view

import com.example.ktask.model.ModelPlaces

interface TaskHandler {
    fun clickHandle(pos:Int,modelPlaces: ModelPlaces)
}
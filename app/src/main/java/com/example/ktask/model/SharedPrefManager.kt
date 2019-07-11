package com.example.ktask.model

import android.content.Context

class SharedPrefManager(private var context: Context) {

    private val SHAREDPREFNAME = "com.example.ktask.model"
    private val STATE = "state"
    private val POSITION = "position"
    private val PLACEID = "placeId"
    private val PLACENM = "placeName"

    //    @Synchronized
    companion object {
        private var mInstance: SharedPrefManager? = null
        fun getmInstance(context: Context): SharedPrefManager? {
            if (mInstance == null) {
                synchronized(SharedPrefManager::class){
                    mInstance = SharedPrefManager(context)
                }
            }
            return mInstance
        }
    }
    fun saveState(state: Boolean): Boolean {
        val preferences = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(STATE, state)
        editor.apply()
        return state
    }
    fun savePosition(pos:Int):Int{
        if(pos!=null) {
            val pref = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putInt(POSITION, pos)
            editor.apply()
        }
        return pos
    }

    fun saveID(placeID: String?): Boolean {
        if (placeID != null) {
            val pr = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)
            val editor = pr.edit()
            editor.putString(PLACEID, placeID)
            editor.apply()
            return true
        } else
            return false
    }

    fun saveName(placeName: String?): Boolean {
        if (placeName != null) {
            val p = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)
            val editor = p.edit()
            editor.putString(PLACENM, placeName)
            editor.apply()
            return true
        } else
            return false
    }
    fun getID(): String? {
        val sharedPref = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)
        return sharedPref.getString(PLACEID, null)
    }
    fun getName(): String? {
        val sp = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)
        return sp.getString(PLACENM, null)
    }
    fun getState(): Boolean {
        val sp = context.getSharedPreferences(SHAREDPREFNAME, Context.MODE_PRIVATE)

        return sp.getBoolean(STATE,false)
    }

    fun getPosition(): Int {
        val sp = context.getSharedPreferences(SHAREDPREFNAME,Context.MODE_PRIVATE)
        return sp.getInt(POSITION,0)

    }
}
package com.example.horoscopo_app.data

import android.content.Context

class Prefs (val context:Context){

    val SHARED_NAME="MyDTB"
    val SHARED_ZODIAC_NAME="zodiacName"
    val SHARED_FAV="fav"

    val storage=context.getSharedPreferences(SHARED_NAME,0)

    fun saveZodiacName(name:String)
    {
        storage.edit().putString(SHARED_ZODIAC_NAME,name).apply()
    }
    fun saveFav(fav:Boolean)
    {
        storage.edit().putBoolean(SHARED_FAV,fav).apply()
    }
    fun getName():String
    {
        return storage.getString(SHARED_ZODIAC_NAME,"")!!
    }
    fun getFav():Boolean
    {
        return storage.getBoolean(SHARED_FAV,false)
    }
    fun wipe()
    {
        storage.edit().clear().apply()
    }

}
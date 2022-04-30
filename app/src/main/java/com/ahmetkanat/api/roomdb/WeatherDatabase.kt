package com.ahmetkanat.api.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmetkanat.api.model.Weather

@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDAO

    companion object{

        @Volatile private var instance : WeatherDatabase? = null

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also{
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,WeatherDatabase::class.java,"weatherdatabase"
        ).build()

    }

}
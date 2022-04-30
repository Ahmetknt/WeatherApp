package com.ahmetkanat.api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class Weather(
    @ColumnInfo(name = "date")
    val date : String?,
    @ColumnInfo(name = "day")
    val day : String?,
    @ColumnInfo(name = "icon")
    val icon : String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "status")
    val status: String?,
    @ColumnInfo(name = "degree")
    val degree: String?,
    @ColumnInfo(name = "min")
    val min: String?,
    @ColumnInfo(name = "max")
    val max: String?,
    @ColumnInfo(name = "night")
    val night: String?,
    @ColumnInfo(name = "humidity")
    val humidity: String?,

){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}
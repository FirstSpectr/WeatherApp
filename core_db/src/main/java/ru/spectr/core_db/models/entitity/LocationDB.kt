package ru.spectr.core_db.models.entitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_locations")
data class LocationDB(
    @PrimaryKey val woeid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "location_type") val locationType: String,
    @ColumnInfo(name = "latt_long") val lattLong: String
)
package ru.spectr.core_db.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.spectr.core_db.models.entitity.LocationDB

@Dao
interface LocationDao {
    @Query("SELECT * FROM favorite_locations")
    suspend fun getAll(): List<LocationDB>

    @Insert
    suspend fun insertAll(vararg users: LocationDB)

    @Query("DELETE FROM favorite_locations WHERE woeid = :woeid")
    suspend fun delete(woeid: Int)
}
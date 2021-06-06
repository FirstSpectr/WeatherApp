package ru.spectr.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.spectr.core_db.models.dao.LocationDao
import ru.spectr.core_db.models.entitity.LocationDB

@Database(entities = [LocationDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
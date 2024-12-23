package com.magaramova.projectfilm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.magaramova.projectfilm.data.Entity.Film
import com.magaramova.projectfilm.data.FilmDao


@Database(entities = [Film::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}
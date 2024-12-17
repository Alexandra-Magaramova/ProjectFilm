package com.magaramova.projectfilm.data

import android.content.ContentValues
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.magaramova.projectfilm.database.DatabaseHelper
import com.magaramova.projectfilm.data.Entity.Film
import java.util.concurrent.Executors
import kotlinx.coroutines.flow.Flow

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
            filmDao.insertAll(films)
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()

}
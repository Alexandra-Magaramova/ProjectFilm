package com.magaramova.projectfilm.data

import com.magaramova.projectfilm.data.Entity.Film
import io.reactivex.rxjava3.core.Observable


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
            filmDao.insertAll(films)
    }

    fun getAllFromDB(): Observable<List<Film>> = filmDao.getCachedFilms()

}
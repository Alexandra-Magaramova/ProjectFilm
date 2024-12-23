package com.magaramova.projectfilm.utils

import com.magaramova.projectfilm.data.Entity.TmdbFilm
import com.magaramova.projectfilm.data.Entity.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

//Конвертируем объект, пришедший с API (TmdbFilm) в объект, который мы кладем в RV (Film)
object Converter {

    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                    title = it.title,
                    poster = it.posterPath,
                    description = it.overview,
                    rating = it.voteAverage,
                    isInFavorites = false
                )
            )
        }
        return result
    }
}
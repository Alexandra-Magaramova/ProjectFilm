package com.magaramova.projectfilm.utils

import com.magaramova.remote_module.entity.TmdbFilm
import com.magaramova.projectfilm.data.Entity.Film

//Конвертируем объект, пришедший с API (TmdbFilm) в объект, который мы кладем в RV (Film)
object Converter {

    fun convertApiListToDtoList(list: List<com.magaramova.remote_module.entity.TmdbFilm>?): List<Film> {
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
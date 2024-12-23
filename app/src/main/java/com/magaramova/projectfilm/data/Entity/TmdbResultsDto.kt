package com.magaramova.projectfilm.data.Entity

import com.google.gson.annotations.SerializedName

//DTO-объект, файл, который приходит ответом от API
data class TmdbResultsDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tmdbFilms: List<TmdbFilm>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

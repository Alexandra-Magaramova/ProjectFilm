package com.magaramova.projectfilm.data

import com.magaramova.projectfilm.data.Entity.TmdbResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//интерфейс, который отвечает за создание методов получения информации с сервера
interface TmdbApi {
    //забираем данные с сервера и указываем путь к ресурсу
    @GET("3/movie/{category}")
    fun getFilms(
        //с помощью аннотаций, что мы будем конкретно забирать
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResultsDto>
}

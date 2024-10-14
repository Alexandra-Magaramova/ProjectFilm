package com.magaramova.projectfilm.domain

import com.magaramova.projectfilm.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}
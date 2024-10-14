package com.magaramova.projectfilm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magaramova.projectfilm.App
import com.magaramova.projectfilm.domain.Film
import com.magaramova.projectfilm.domain.Interactor

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    //Инициализируем интерактор
    private var interactor: Interactor = App.instance.interactor
    init {

        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}
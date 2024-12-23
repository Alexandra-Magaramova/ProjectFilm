package com.magaramova.projectfilm.domain


import com.magaramova.projectfilm.data.*
import com.magaramova.projectfilm.data.Entity.Film
import com.magaramova.projectfilm.data.Entity.TmdbFilm
import com.magaramova.projectfilm.data.Entity.TmdbResultsDto
import com.magaramova.projectfilm.utils.Converter
import com.magaramova.projectfilm.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class Interactor(
    private val repo: MainRepository,
    private val retrofitService: TmdbApi,
    private val preferences: PreferenceProvider
) {
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)

        //Метод getDefaultCategoryFromPreferences() будет получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page)
            .enqueue(object : Callback<TmdbResultsDto> {
                override fun onResponse( call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                    val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                    //Кладем фильмы в бд
                    //В случае успешного ответа кладем фильмы в БД и выключаем ProgressBar
                    Completable.fromSingle<List<Film>> {
                        repo.putToDb(list)
                    }
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                    progressBarState.onNext(false)
                }

                override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                    //В случае провала выключаем ProgressBar
                    progressBarState.onNext(false)
                }
            })
    }

    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    //делает ссылку от репозитория к View модели
    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()
}



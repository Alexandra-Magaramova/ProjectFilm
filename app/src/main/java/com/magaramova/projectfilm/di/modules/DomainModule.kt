package com.magaramova.projectfilm.di.modules

import android.content.Context
import com.magaramova.projectfilm.data.MainRepository
import com.magaramova.projectfilm.data.PreferenceProvider
import com.magaramova.projectfilm.data.TmdbApi
import com.magaramova.projectfilm.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

    @Module
//Передаем контекст для SharedPreferences через конструктор
    class DomainModule(val context: Context) {
        //Нам нужно контекст как-то провайдить, поэтому создаем такой метод
        @Provides
        fun provideContext() = context

        @Singleton
        @Provides
        //Создаем экземпляр SharedPreferences
        fun providePreferences(context: Context) = PreferenceProvider(context)

        @Singleton
        @Provides
        fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
    }

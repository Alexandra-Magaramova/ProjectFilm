package com.magaramova.projectfilm.di.modules

import com.magaramova.projectfilm.data.MainRepository
import com.magaramova.projectfilm.data.TmdbApi
import com.magaramova.projectfilm.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}
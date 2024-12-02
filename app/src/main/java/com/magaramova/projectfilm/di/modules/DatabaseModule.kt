package com.magaramova.projectfilm.di.modules

import android.content.Context
import androidx.room.Room
import com.magaramova.projectfilm.data.FilmDao
import com.magaramova.projectfilm.data.MainRepository
import com.magaramova.projectfilm.database.AppDatabase
import com.magaramova.projectfilm.database.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "film_db").build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}
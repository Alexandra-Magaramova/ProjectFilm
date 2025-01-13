package com.magaramova.remote_module

//интерфейс, который будет провайдить Retrofit сервис
interface RemoteProvider {
    fun provideRemote(): TmdbApi
}
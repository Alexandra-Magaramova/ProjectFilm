package com.magaramova.projectfilm

import android.app.Application
import com.magaramova.projectfilm.di.AppComponent
import com.magaramova.projectfilm.di.DaggerAppComponent
import com.magaramova.projectfilm.di.modules.DatabaseModule
import com.magaramova.projectfilm.di.modules.DomainModule
import com.magaramova.projectfilm.di.modules.RemoteModule


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }
}

package com.magaramova.projectfilm

import android.app.Application
import com.magaramova.projectfilm.di.AppComponent
import com.magaramova.projectfilm.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}

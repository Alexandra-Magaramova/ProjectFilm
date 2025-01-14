package com.magaramova.projectfilm.di

import com.magaramova.projectfilm.di.modules.DatabaseModule
import com.magaramova.projectfilm.di.modules.DomainModule
import  com.magaramova.remote_module.RemoteProvider
import com.magaramova.projectfilm.viewmodel.HomeFragmentViewModel
import com.magaramova.projectfilm.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    dependencies = [RemoteProvider::class],
    modules = [
        DatabaseModule::class,
        DomainModule::class
    ]
)

interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

    //метод для того, чтобы появилась возможность внедрять зависимости в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}
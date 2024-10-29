package com.magaramova.projectfilm.di

import com.magaramova.projectfilm.di.modules.DatabaseModule
import com.magaramova.projectfilm.di.modules.DomainModule
import com.magaramova.projectfilm.di.modules.RemoteModule
import com.magaramova.projectfilm.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}
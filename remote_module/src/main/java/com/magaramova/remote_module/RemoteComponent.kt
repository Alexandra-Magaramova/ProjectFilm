package com.magaramova.remote_module


import com.magaramova.remote_module.entity.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RemoteModule::class]
)
interface RemoteComponent : RemoteProvider
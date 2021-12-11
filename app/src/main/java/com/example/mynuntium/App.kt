package com.example.mynuntium

import android.app.Application
import com.example.mynuntium.di.component.AppComponent
import com.example.mynuntium.di.component.DaggerAppComponent
import com.example.mynuntium.di.module.DatabaseModule

class App: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }


}
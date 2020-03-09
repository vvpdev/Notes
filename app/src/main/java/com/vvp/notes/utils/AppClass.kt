package com.vvp.notes.utils

import android.app.Application
import com.vvp.notes.di.DaggerDiComponent
import com.vvp.notes.di.DateBaseModule
import com.vvp.notes.di.DiComponent

class AppClass: Application() {


    companion object {
        var diComponent:DiComponent? = null
    }

    override fun onCreate() {
        super.onCreate()


        // создание модуля БД
        diComponent = DaggerDiComponent.builder().dateBaseModule(DateBaseModule(context = applicationContext)).build()
    }

}
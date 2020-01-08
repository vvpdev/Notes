package com.vvp.notes.utils

import android.app.Application
import com.vvp.repository.room.RoomDB

class AppClass: Application() {


    companion object {
        lateinit var dateBase: RoomDB
    }


    override fun onCreate() {
        super.onCreate()
        dateBase = RoomDB.getInstance(context = applicationContext)
    }

}
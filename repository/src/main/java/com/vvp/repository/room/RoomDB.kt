package com.vvp.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vvp.repository.model.Note


@Database (entities = [Note::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

        abstract fun noteDAO(): DAO


    companion object {

        private var Instance: RoomDB? = null

        fun getInstance(context: Context): RoomDB {

            if (Instance == null){
                Instance = Room.databaseBuilder(

                                // передаем контекст
                                context,

                                RoomDB::class.java,

                                //название БД
                                "notesRoom")

                                .fallbackToDestructiveMigration()

                                //создание
                                .build()
            }

            return Instance as RoomDB
        }
    }

}
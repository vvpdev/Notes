package com.vvp.notes.repository

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class DateBase: RoomDatabase() {

    abstract fun getDao():MethodsDao
}
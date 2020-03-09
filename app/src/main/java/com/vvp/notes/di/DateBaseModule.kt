package com.vvp.notes.di

import android.content.Context
import androidx.room.Room
import com.vvp.notes.repository.DateBase
import com.vvp.notes.repository.MethodsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DateBaseModule(private val context: Context) {


    @Singleton
    @Provides
    fun provideDateBase(): DateBase {
        return Room.databaseBuilder(context, DateBase::class.java, "DateBase").fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun provideDao(newsDateBase: DateBase): MethodsDao {
        return newsDateBase.getDao()
    }








}
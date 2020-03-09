package com.vvp.notes.di

import com.vvp.notes.repository.Repository
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {


    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }
}
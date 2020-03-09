package com.vvp.notes.di

import com.vvp.notes.presenters.EditPresenter
import com.vvp.notes.presenters.ListPresenter
import com.vvp.notes.repository.Repository
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DateBaseModule::class, RepositoryModule::class])

@Singleton
interface DiComponent {

    fun injectListPresenter(presenter: ListPresenter)

    fun injectEditPresenter(presenter: EditPresenter)

    //fun injectDetailsPresenter (presenter: DetailsPresenter)

    fun injectRepository(repository: Repository)
}
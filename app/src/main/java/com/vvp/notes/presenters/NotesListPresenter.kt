package com.vvp.notes.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.notes.providers.NotesProvider
import com.vvp.notes.views.NotesListView
import com.vvp.repository.modelNote.Note


// список заметок

@InjectViewState
class NotesListPresenter: MvpPresenter<NotesListView>() {


    private var provider: NotesProvider = NotesProvider()

    // запуск при инжектировании
    init {

        if (this.provider.getNoteList().isEmpty()){

            viewState.showError("у Вас еще нет заметок")
        }
        else{
            viewState.showNotes(this.provider.getNoteList())
        }
    }





}
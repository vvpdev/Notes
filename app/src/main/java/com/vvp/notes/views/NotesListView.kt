package com.vvp.notes.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.repository.modelNote.Note

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NotesListView: MvpView {

    fun setupRecView()

    fun showNotes(arrayNotesList: ArrayList<Note>)

    fun showError(message: String)

}
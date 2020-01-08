package com.vvp.notes.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.repository.model.Note


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NoteScreenView: MvpView {

    fun transferIdItemToPresenter()

    fun loadNoteToScreen(note: Note)

    fun getNoteChange()

    fun showDialogAlert()

    fun backToNotesList()
}
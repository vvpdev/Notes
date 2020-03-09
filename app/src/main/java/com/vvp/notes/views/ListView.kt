package com.vvp.notes.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.notes.repository.NoteModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ListView: MvpView {

    fun showNotes(notesList: List<NoteModel>)

    fun showError(message: Int)

    @StateStrategyType(value = SkipStrategy::class)
    fun showToast(message: Int)

}
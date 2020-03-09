package com.vvp.notes.views

import com.arellomobile.mvp.MvpView

interface EditView: MvpView {


    fun dataToSave()

    fun backToNoteList()

    fun showMessage(message: Int)

    fun getNote()

    fun showAlertDialog()

    fun checkChanges()

}
package com.vvp.notes.presenters

import android.os.Build
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.notes.R
import com.vvp.notes.repository.NoteModel
import com.vvp.notes.repository.Repository
import com.vvp.notes.utils.AppClass
import com.vvp.notes.views.EditView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


@InjectViewState
class EditPresenter: MvpPresenter<EditView>() {


    @Inject
    lateinit var repository: Repository


    init {
        AppClass.diComponent?.injectEditPresenter(presenter = this)
    }


    fun saveNote(title: String, text :String){

        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val note = NoteModel(title = title, text = text, date = date.toString())

        CoroutineScope(Dispatchers.IO).launch {

            if (repository.insertNote(note = note)){

                CoroutineScope(Dispatchers.Main).launch {
                    viewState.showMessage(R.string.note_saved)
                    viewState.backToNoteList()
                }
            }
        }
    }


    fun updateNote(id: Int, title: String, text: String){

        CoroutineScope(Dispatchers.IO).launch {

            if (repository.updateNote(id = id, title = title, text = text)){

                CoroutineScope(Dispatchers.Main).launch {
                    viewState.showMessage(R.string.note_updated)
                    viewState.backToNoteList()
                }
            }
        }
    }




}
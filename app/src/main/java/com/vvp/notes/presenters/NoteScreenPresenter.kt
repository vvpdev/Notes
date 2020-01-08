package com.vvp.notes.presenters

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.notes.utils.AppClass.Companion.dateBase
import com.vvp.notes.views.NoteScreenView
import com.vvp.repository.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


// подробный показ и редактирование заметки

@InjectViewState
class NoteScreenPresenter

    constructor(private var context: Context): MvpPresenter<NoteScreenView>() {


    //переменные для новой заметки
    private var newTitleForSave: String? = null
    private var newBodyForSave: String? = null

    // переданный Id заметки
    private var idNote: Int? = null

    private val job = Job()
    private val coroutineScope: CoroutineScope = CoroutineScope(job + Dispatchers.Main)


    // загрузка на макет из провайдера / БД
    fun loadFullNote(idNote: Int){

        // переопределим переменную для других методов
        this.idNote = idNote

        if (this.idNote != 100000 && this.idNote != null){

            coroutineScope.launch {
                context.let {
                    viewState.loadNoteToScreen(dateBase.noteDAO().fetchOneNoteAsync(idNote))
                }
            }
        }
    }



    // проверка текста с макета с тем, что был загружен изначально
    fun onSaveNoteChange(newTitleNote: String, newBodyNote: String) {

        // если пустые поля
        if (newTitleNote == "" && newBodyNote == ""){
            viewState.backToNotesList() }

        else{
            //если переданный id не null - т.е. загружена заметка
            if (this.idNote != null && this.idNote != 100000){

              CoroutineScope(Dispatchers.Main).launch {

                    context.let {

                        if (newTitleNote == dateBase.noteDAO().fetchOneNoteAsync(idNote!!).title

                            && newBodyNote == dateBase.noteDAO().fetchOneNoteAsync(idNote!!).bodyNote) {

                            // изменений нет - выход на экран заметок
                            viewState.backToNotesList() }

                        else {
                            // показ диалога с вопросом о сохранении
                            viewState.showDialogAlert()
                        }
                    }
                }
            }

            else{

                // если id null, но поля заполнены - создание новой заметки
                this.newTitleForSave = newTitleNote
                this.newBodyForSave = newBodyNote

                // диалог о предложении сохранить новую заметку
                viewState.showDialogAlert()
            }
        }
    }


    // сохранение
    fun saveNewNote(){

        val newNote: Note? = newTitleForSave?.let { newBodyForSave?.let { it1 -> Note(it, it1) } }


        CoroutineScope(Dispatchers.Main).launch {
            context.let {

                CoroutineScope(Dispatchers.Main).launch {

                    context.let {

                        if (newNote != null) {
                            dateBase.run { dateBase.run { noteDAO().insertNote(note = newNote) } }
                        }
                    }
                }
            }
        }

        // выход на экран списка заметок
        viewState.backToNotesList()
    }


    override fun onDestroy() {

        this.idNote = null
        this.newTitleForSave = null
        this.newBodyForSave = null


        super.onDestroy() }
}
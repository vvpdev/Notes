package com.vvp.notes.presenters

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.notes.utils.AppClass.Companion.dateBase
import com.vvp.notes.views.NotesListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    // список заметок

@InjectViewState
class NotesListPresenter constructor(context: Context) : MvpPresenter<NotesListView>() {


        init {

            // настройка RecyclerView
            viewState.setupRecView()

            CoroutineScope(Dispatchers.Main).launch {

            context.let {

             val notesFromDB = dateBase.noteDAO().fetchAllNotesAsync()

             if (notesFromDB.isEmpty()){
                 viewState.showError("у Вас еще нет заметок")
             }
             else{
                 viewState.showNotes(notesFromDB)
             }
            }
           }
         }


    // передача презентеру позиции нажатого айтема
    fun onClick(itemPosition: Int){
        viewState.toDetailsNote(itemPosition)
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}
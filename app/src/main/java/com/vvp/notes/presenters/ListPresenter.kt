package com.vvp.notes.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.notes.R
import com.vvp.notes.repository.Repository
import com.vvp.notes.utils.AppClass
import com.vvp.notes.views.ListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@InjectViewState
class ListPresenter: MvpPresenter<ListView>() {


    @Inject
    lateinit var repository: Repository


    init {
        AppClass.diComponent?.injectListPresenter(presenter = this)

    }



    fun fetchAllNotes(){

       CoroutineScope(Dispatchers.IO).launch {

           val noteList = repository.getAllNotes()

           CoroutineScope(Dispatchers.Main).launch {
               if (noteList.isNullOrEmpty()){
                   viewState.showError(R.string.empty_notes_list)
               } else{
                   viewState.showNotes(notesList = noteList)
               }
           }
       }
    }



    fun deleteSelectedNote(id: Int){

        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(id = id)

            val noteList = repository.getAllNotes()

            CoroutineScope(Dispatchers.Main).launch {

                if (!noteList.isNullOrEmpty()){
                    viewState.showNotes(notesList = noteList)
                } else {
                    viewState.showError(message = R.string.empty_notes_list)
                }
            }
        }
    }




    override fun onDestroy() {
        super.onDestroy()
    }


}
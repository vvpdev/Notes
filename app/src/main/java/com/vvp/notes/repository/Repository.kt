package com.vvp.notes.repository

import com.vvp.notes.utils.AppClass
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class Repository {


    @Inject
    lateinit var dao: MethodsDao


    init {
        AppClass.diComponent?.injectRepository(repository = this)
    }


    suspend fun getAllNotes(): List<NoteModel> {

        return CoroutineScope(Dispatchers.IO).async {

            return@async dao.getAllNotes()
        }.await()
    }


    suspend fun insertNote(note: NoteModel): Boolean {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                dao.insertNote(noteModel = note)
                return@async true
            }
            catch (e: Exception){
                return@async false
            }
        }.await()
    }


    suspend fun updateNote(id: Int, title: String, text: String): Boolean {

        return CoroutineScope(Dispatchers.IO).async {
            try {
                dao.updateNote(id = id, title = title, text = text)
                return@async true
            } catch (e: Exception){
                return@async false
            }
        }.await()
    }



    suspend fun deleteNote(id: Int): Boolean {
        return CoroutineScope(Dispatchers.IO).async {
            dao.deleteNote(id = id)
            return@async true
        }.await()
    }





}
package com.vvp.notes.repository

import androidx.room.*


@Dao
interface MethodsDao {


    @Insert
    fun insertNote(noteModel: NoteModel)

    @Query("SELECT * FROM NoteModel")
    fun getAllNotes(): List<NoteModel>

    @Query("DELETE FROM NoteModel WHERE id = :id")
    fun deleteNote(id: Int)

    @Query("UPDATE NoteModel SET title = :title, text = :text WHERE id = :id")
    fun updateNote(id: Int, title: String, text: String)
}
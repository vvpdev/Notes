package com.vvp.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vvp.repository.model.Note

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    // получить все заметки
    @Query("SELECT * FROM notesTable")
    suspend fun fetchAllNotesAsync(): List<Note>


    // получить заметку по Id
    @Query("SELECT * FROM notesTable WHERE id = :selectedId")
    suspend fun fetchOneNoteAsync(selectedId: Int): Note
}
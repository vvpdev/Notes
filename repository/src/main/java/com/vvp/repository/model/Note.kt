package com.vvp.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "notesTable")
data class Note (

    var title: String,

    var bodyNote: String) {

    // id не в первичном конструкторе
    @PrimaryKey
        (autoGenerate = true)
    var id: Int = 0
}

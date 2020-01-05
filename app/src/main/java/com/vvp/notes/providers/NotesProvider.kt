package com.vvp.notes.providers

import com.vvp.repository.modelNote.Note

class NotesProvider {




        fun getNoteList(): ArrayList<Note>{

            val arrayNotesList: ArrayList<Note> = ArrayList()

            arrayNotesList.add(Note(1, "заголовок 1", " британский пехотный танк периода Второй мировой войны, лёгкий по массе. Спроектирован в 1938 году фирмой Vickers-Armstrongs. За время серийного выпуска, с июня 1940 по апрель 1944 года (по некоторым данным — до начала 1945 года), Великобританией и Канадой выпущено 8275 «Валентайнов»,", "сегодня"))
            arrayNotesList.add(Note(2, "заголовок 2", "что сделало его самым многочисленным британским танком Второй мировой[1]. Состоял на вооружении Великобритании и ряда стран Британского Содружества, также в значительных количествах поставлялся по программе ленд-лиза в СССР. Использовался вплоть", "вчера"))
            arrayNotesList.add(Note(3, "заголовок 3", "до 1945 года и признан военными специалистами одним из наиболее удачных танков в своём классе[2]. После войны оставался на вооружении Новой Зеландии до 1955 года[3].", "завтра"))
            arrayNotesList.add(Note(4, "заголовок 4", "тело заметки 4", "позавчера"))
            arrayNotesList.add(Note(5, "заголовок 5", "тело заметки 5", "послезавтра"))

            return arrayNotesList
        }


}
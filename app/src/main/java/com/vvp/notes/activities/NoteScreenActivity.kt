package com.vvp.notes.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vvp.notes.R
import com.vvp.notes.presenters.NoteScreenPresenter
import com.vvp.notes.views.NoteScreenView
import com.vvp.repository.model.Note


class NoteScreenActivity : MvpAppCompatActivity(), NoteScreenView {


    @InjectPresenter
    lateinit var presenter: NoteScreenPresenter


    @ProvidePresenter
    fun providePresenter(): NoteScreenPresenter {
        return NoteScreenPresenter(applicationContext)
    }


    private lateinit var editDetailsTitleNote: EditText
    private lateinit var editDetailsBodyNote: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_screen)


        //UI
        this.editDetailsTitleNote = findViewById(R.id.editDetailsTitleNote)
        this.editDetailsBodyNote = findViewById(R.id.editDetailsBodyNote)


        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        transferIdItemToPresenter()
    }



    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.note_screen_menu, menu)
        return super.onCreateOptionsMenu(menu) }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_item_save_note ->  return true   // предача презентеру инфы на сохранение

            android.R.id.home -> getNoteChange() }

            return true
    }



    // прием переданного id элемента и отправка презентеру для загрузки всей заметки
    override fun transferIdItemToPresenter() {

        val idItem: Int = intent.getIntExtra("position", 100000)
        presenter.loadFullNote(idItem)
    }


    // загрузка данных на макет
    override fun loadNoteToScreen(note: Note) {

        editDetailsTitleNote.setText(note.title, TextView.BufferType.EDITABLE)
        editDetailsBodyNote.setText(note.bodyNote, TextView.BufferType.EDITABLE)
    }


    //функция берет текст из полей заполнения и передает презентеру для сравнения
    override fun getNoteChange() {

        val newTitleNote: String = editDetailsTitleNote.text.toString()
        val newBodyNote: String = editDetailsBodyNote.text.toString()
        presenter.onSaveNoteChange(newTitleNote, newBodyNote)
    }


    // окно с вопросом при сохранениии
    override fun showDialogAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.question_save_change)

        builder.setPositiveButton(R.string.answer_yes){dialog, which ->

            presenter.saveNewNote()
        }

        builder.setNegativeButton(R.string.answer_no){dialog,which ->

            backToNotesList()
        }

        builder.setNeutralButton(R.string.cancel){dialog ,_ ->

            dialog.cancel() }

        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



    // назад к списку заметок
    override fun backToNotesList() {

        startActivity(Intent(this, NotesListActivity::class.java))

       // завершить активити
        super.finish()
    }



    override fun onBackPressed() {
        //super.onBackPressed()
        getNoteChange()
    }


    override fun onPause() {

        presenter.detachView(this)

        super.onPause()
    }


    override fun onResume() {

        presenter.attachView(this)

        super.onResume()
    }


    override fun onDestroy() {

        presenter.onDestroy()

        super.onDestroy()
    }
}

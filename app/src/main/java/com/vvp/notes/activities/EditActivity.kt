package com.vvp.notes.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.notes.R
import com.vvp.notes.presenters.EditPresenter
import com.vvp.notes.repository.NoteModel
import com.vvp.notes.views.EditView
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : MvpAppCompatActivity(), EditView {


    @InjectPresenter
    lateinit var presenter: EditPresenter

    lateinit var note: NoteModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)


        getNote()
    }

    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.save_note_item -> { dataToSave() }
            android.R.id.home -> { checkChanges() }
        }
        return super.onOptionsItemSelected(item)
    }




    //view implementation

    override fun dataToSave() {

        try {
            if (note.text == null || note.text == ""){
                val title = editTitleNote.text.toString()
                val text = editTextNote.text.toString()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    presenter.saveNote(title = title, text = text)
                }
            }
            else{
                presenter.updateNote(id = note.id, title = editTitleNote.text.toString(), text = editTextNote.text.toString())
            }
        }
        catch (e: Exception){

            Log.i("NoteAppLog", "Exception  note is null")

            if (editTextNote.text.toString() == ""){
                showMessage(message = R.string.error_empty_text)
            } else {
                presenter.saveNote(title = editTitleNote.text.toString(), text = editTextNote.text.toString())
            }
        }
    }


    override fun showMessage(message: Int) {
        Toast.makeText(applicationContext, getText(message), Toast.LENGTH_SHORT).show()
    }


    override fun getNote() {

        val note: NoteModel? = intent.extras?.getParcelable("note")

        if (note != null){
            editTitleNote.setText(note.title)
            editTextNote.setText(note.text)
            this.note = note
        }
    }


    override fun backToNoteList() {
        finish()
    }


    override fun showAlertDialog() {

        AlertDialog.Builder(this)
            .setTitle(R.string.question_save_change)

            .setPositiveButton(R.string.answer_yes) { _, _ ->

                presenter.updateNote(id = note.id, title = editTitleNote.text.toString(), text = editTextNote.text.toString())
            }
            .setNegativeButton(R.string.answer_no) { dialog, _ ->
                dialog.cancel()
                finish()
            }
            .setCancelable(true)
            .create()
            .show()
    }



    override fun checkChanges() {

        try {
            if (editTextNote.text.toString() != note.text || editTitleNote.text.toString() != note.title){
                showAlertDialog()
            } else{
                super.onBackPressed()
            }
        }
        catch (e: Exception){
            super.onBackPressed()
        }
    }


    override fun onBackPressed() {
        checkChanges()
    }

}

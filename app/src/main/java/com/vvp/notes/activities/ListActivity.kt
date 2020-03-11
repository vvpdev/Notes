package com.vvp.notes.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.notes.R
import com.vvp.notes.adapters.ListNotesAdapter
import com.vvp.notes.presenters.ListPresenter
import com.vvp.notes.repository.NoteModel
import com.vvp.notes.views.ListView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity :  MvpAppCompatActivity(), ListView, ListNotesAdapter.ItemClickListener {


    @InjectPresenter
    lateinit var presenter: ListPresenter

    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: ListNotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        // setup recyclerView
        manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerListNotes.layoutManager = manager
        adapter = ListNotesAdapter(clickListener = this)
        recyclerListNotes.adapter = adapter


        // переход к созданию новой заметки
        floatButtonNewNote.setOnClickListener{ startActivity(Intent(this, EditActivity::class.java)) }

    }


    override fun onPostResume() {
        super.onPostResume()
        presenter.fetchAllNotes()
    }



    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.menu_item_about_app ->
                // вызов алерт диалога
                return true }
        return super.onOptionsItemSelected(item)
    }




    //view implementation

    override fun showNotes(notesList: List<NoteModel>) {

        textError.visibility = View.GONE
        editTextForSearch.visibility = View.VISIBLE
        recyclerListNotes.visibility = View.VISIBLE
        adapter.loadData(notesList)
    }



    override fun showError(message: Int) {

        editTextForSearch.visibility = View.GONE
        recyclerListNotes.visibility = View.GONE
        textError.visibility = View.VISIBLE
        textError.text = getText(message)
    }



    override fun showToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    // open selected item
    override fun onItemClick(note: NoteModel) {

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("selectedNote", note)
        startActivity(intent)
    }


    // delete selected note
    override fun onLongItemClick(id: Int) {

        AlertDialog.Builder(this)
            .setTitle(R.string.question_delete_selected_note)

            .setPositiveButton(R.string.answer_yes) { _, _ ->

                presenter.deleteSelectedNote(id = id)
            }
            .setNegativeButton(R.string.answer_no) { dialog, _ ->
                dialog.cancel()
            }
            .setCancelable(true)
            .create()
            .show()
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }



    override fun onPause() {

        presenter.detachView(this)
        super.onPause() }


    override fun onResume() {

        presenter.attachView(this)
        //adapter = ListNotesAdapter(this)
        super.onResume() }


    override fun onDestroy() {

        presenter.onDestroy()
        super.onDestroy() }
}

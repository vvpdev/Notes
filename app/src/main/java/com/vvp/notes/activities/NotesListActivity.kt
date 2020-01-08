package com.vvp.notes.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vvp.notes.R
import com.vvp.notes.adapters.ListNotesAdapter
import com.vvp.notes.presenters.NotesListPresenter
import com.vvp.notes.views.NotesListView
import com.vvp.repository.model.Note

class NotesListActivity :  MvpAppCompatActivity(), NotesListView, ListNotesAdapter.ItemClickListener {



    @InjectPresenter
    lateinit var presenter: NotesListPresenter

    // добавление презентера с параметрами
    // просто своя функция, возвращающая экземпляр презентера с параметрами конструктора
    @ProvidePresenter
    fun providePresenter() : NotesListPresenter {
        return NotesListPresenter(applicationContext)
    }


    //UI
    private lateinit var recyclerListNotes: RecyclerView
    private lateinit var textError: TextView
    private lateinit var layoutManager: LinearLayoutManager
    private var adapter: ListNotesAdapter? = null
    private lateinit var floatButtonNewNote: FloatingActionButton
    private lateinit var editTextForSearch: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)


        //find UI
        this.recyclerListNotes = findViewById(R.id.recyclerListNotes)
        this.textError = findViewById(R.id.textError)
        this.floatButtonNewNote = findViewById(R.id.floatButtonNewNote)
        this.editTextForSearch = findViewById(R.id.editTextForSearch)





        // переход к созданию новой заметки
        floatButtonNewNote.setOnClickListener{ startActivity(Intent(this,NoteScreenActivity::class.java)) }
    }



    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.note_list_menu, menu)
        return super.onCreateOptionsMenu(menu) }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.menu_item_about_app ->
                // вызов алерт диалога
                return true }
        return super.onOptionsItemSelected(item) }



    // настройка recyclerView
    override fun setupRecView() {

        this.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        this.recyclerListNotes.layoutManager = this.layoutManager

        // сам фрагмент имплементирует интерфейс слушателя
        this.adapter = ListNotesAdapter(this)
    }



    // показ заметок
    override fun showNotes(notesList: List<Note>) {

        this.textError.visibility = View.GONE
        this.editTextForSearch.visibility = View.VISIBLE
        this.recyclerListNotes.visibility = View.VISIBLE

        this.recyclerListNotes.adapter = this.adapter
        adapter!!.setupAdapter(notesList)        // передаем массив
    }



    // показ ошибок и сообщений
    override fun showError(message: String) {

        this.editTextForSearch.visibility = View.GONE
        this.textError.visibility = View.VISIBLE
        this.recyclerListNotes.visibility = View.GONE
        this.textError.text = message }



    // показ тоста для уведомления об различных операциях
    override fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show() }



    // переход к просмотру деталей заметки
    override fun toDetailsNote(itemPosition: Int) {

        val intentToNote = Intent(this, NoteScreenActivity::class.java)
        intentToNote.putExtra("position", itemPosition)
        startActivity(intentToNote)
        super.finish()
    }



    //_____________________________________________________________________________________________
    //Click listener implementation

    override fun onItemClick(item: Note, position: Int) {
        presenter.onClick(position)
    }



    // снэкбар для выхода доделать
    override fun onBackPressed() {

        super.onBackPressed()
    }



    override fun onPause() {

        presenter.detachView(this)
        adapter = null
        super.onPause() }


    override fun onResume() {

        presenter.attachView(this)
        adapter = ListNotesAdapter(this)
        super.onResume() }


    override fun onDestroy() {

        presenter.onDestroy()
        super.onDestroy() }
}

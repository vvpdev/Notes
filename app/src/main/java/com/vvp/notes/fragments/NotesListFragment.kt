package com.vvp.notes.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.notes.R
import com.vvp.notes.adapters.recListNotesAdapter
import com.vvp.notes.presenters.NotesListPresenter
import com.vvp.notes.views.NotesListView
import com.vvp.repository.modelNote.Note

class NotesListFragment : MvpAppCompatFragment(), NotesListView {



    @InjectPresenter
    lateinit var presenter: NotesListPresenter

    //UI
    private lateinit var recyclerListNotes: RecyclerView
    private lateinit var textError: TextView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: recListNotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_notes, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //UI
        this.recyclerListNotes = view.findViewById(R.id.recyclerListNotes)
        this.textError = view.findViewById(R.id.textError)



        setupRecView()
    }



    //_____________________________________________________________________________________________
    // View implementation

    // настройка recyclerView для списка заметок
    override fun setupRecView() {

        this.adapter = recListNotesAdapter()
        this.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        this.recyclerListNotes.layoutManager = this.layoutManager
        this.recyclerListNotes.adapter = this.adapter
    }

    // показ заметок
    override fun showNotes(arrayNotesList: ArrayList<Note>) {

        textError.visibility = View.GONE
        recyclerListNotes.visibility = View.VISIBLE

        adapter.setupAdapter(arrayListNotes = arrayNotesList)   // передаем массив
    }

    // показ ошибок и сообщений
    override fun showError(message: String) {

        textError.visibility = View.VISIBLE
        recyclerListNotes.visibility = View.GONE
        textError.text = message
    }
}

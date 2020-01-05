package com.vvp.notes.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.notes.R
import com.vvp.notes.presenters.NotesListPresenter
import com.vvp.notes.views.NotesListView

class NotesListFragment : MvpAppCompatFragment(), NotesListView {



    @InjectPresenter
    lateinit var presenter: NotesListPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_notes, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    //_____________________________________________________________________________________________
    // View implementation

    // показ заметок
    override fun showNotes() {

    }


    // показ ошибок
    override fun showError(message: String) {

    }
}

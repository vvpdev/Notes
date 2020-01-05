package com.vvp.notes.activities

import android.os.Bundle
import androidx.navigation.findNavController
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vvp.notes.R

class NotesActivity :  MvpAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

    }


    // переопеределение функции для поиска контроллера (включение)
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navFragHost).navigateUp() }

}

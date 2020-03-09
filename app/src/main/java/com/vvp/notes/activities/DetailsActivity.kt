package com.vvp.notes.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vvp.notes.R
import com.vvp.notes.databinding.ActivityDetailsBinding
import com.vvp.notes.repository.NoteModel


class DetailsActivity : AppCompatActivity() {


    lateinit var binding: ActivityDetailsBinding

    lateinit var note: NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        getNoteForShow()
    }


    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.edit_note_item -> { toEditNote() }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getNoteForShow(){

        val note: NoteModel? = intent.extras?.getParcelable("selectedNote")

        if (note != null){
            binding.note = note
            this.note = note
        }
    }



    private fun toEditNote(){
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("note", note)
        startActivity(intent)
    }

}

package com.vvp.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvp.notes.R
import com.vvp.repository.modelNote.Note

class recListNotesAdapter: RecyclerView.Adapter<recListNotesAdapter.ViewHolder>() {

    private var arrayListNotes: ArrayList<Note> = ArrayList()


    fun setupAdapter(arrayListNotes: ArrayList<Note>){

        this.arrayListNotes.clear()

        this.arrayListNotes.addAll(elements = arrayListNotes)

        //обновляем изменения
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

                        //itemView  из класса ViewHolder
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_note, viewGroup, false))
    }


    override fun getItemCount(): Int {

        return this.arrayListNotes.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // привязываем каждую ячейку к элементу массива
        holder.bindElements(note = this.arrayListNotes[position])
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        //find UI elements
        private var textTitleNote: TextView = itemView.findViewById(R.id.textTitleNote)
        private var textBodyNote: TextView = itemView.findViewById(R.id.textBodyNote)
        private var textEditDate: TextView = itemView.findViewById(R.id.textEditDate)

        // связка модели и UI
        fun bindElements(note: Note){

            textTitleNote.text = note.title
            textBodyNote.text = note.bodyNote
            textEditDate.text = note.dateEdit
        }
    }

}
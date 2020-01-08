package com.vvp.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vvp.notes.R
import com.vvp.repository.model.Note



class ListNotesAdapter (private var clickListener: ItemClickListener): RecyclerView.Adapter<ListNotesAdapter.ViewHolder>() {


    private var arrayListNotes: ArrayList<Note> = ArrayList()

    fun setupAdapter(listNotes: List<Note>){

        this.arrayListNotes.clear()

        this.arrayListNotes.addAll(elements = listNotes)

        //обновляем изменения
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_note, viewGroup, false))
    }


    override fun getItemCount(): Int {

        return this.arrayListNotes.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bindElements(note = this.arrayListNotes[position], action = clickListener)
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        //find UI elements
        private var textTitleNote: TextView = itemView.findViewById(R.id.textTitleNote)
        private var textBodyNote: TextView = itemView.findViewById(R.id.textBodyNote)
        private var textEditDate: TextView = itemView.findViewById(R.id.textEditDate)
        private var cardTitleNote: CardView = itemView.findViewById(R.id.cardTitleNote)


        // связка модели и UI
        fun bindElements(note: Note, action: ItemClickListener){

            if (note.title.isEmpty()){
                this.cardTitleNote.visibility = View.GONE
            }
            else{
                this.textTitleNote.text = note.title
            }

            this.textBodyNote.text = note.bodyNote
            //this.textEditDate.text = note.dateEdit

            itemView.setOnClickListener{ action.onItemClick(item = note, position = adapterPosition) }
        }
    }


        //listener interface
        interface ItemClickListener{

         fun onItemClick(item: Note, position: Int)
        }

}
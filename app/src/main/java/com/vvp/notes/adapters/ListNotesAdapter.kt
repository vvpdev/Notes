package com.vvp.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vvp.notes.R
import com.vvp.notes.repository.NoteModel
import com.vvp.notes.utils.NoteDiffUtil


class ListNotesAdapter (private var clickListener: ItemClickListener): RecyclerView.Adapter<ListNotesAdapter.ViewHolder>() {


    private var currentList: ArrayList<NoteModel> = ArrayList()


<<<<<<< HEAD
    fun setupAdapter(newList: List<NoteModel>){

        this.currentList.clear()

        this.currentList.addAll(elements = newList)

       
        notifyDataSetChanged()
=======
    fun loadData(newList: List<NoteModel>){

        if (newList != currentList){
            val diffUtil = NoteDiffUtil(oldList = currentList, newList = newList)
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtil)

            currentList.clear()
            currentList.addAll(elements = newList)

            diffResult.dispatchUpdatesTo(this)
        }
>>>>>>> add long click listener for recyclerView
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_note, viewGroup, false))
    }


    override fun getItemCount(): Int {
        return currentList.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindElements(note = this.currentList[position], action = clickListener)

        //long click listener
        holder.itemView.setOnLongClickListener {
            clickListener.onLongItemClick(id = this.currentList[position].id)
            false
        }


    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        //find UI elements
        private var textTitleNote: TextView = itemView.findViewById(R.id.textTitleNote)
        private var textBodyNote: TextView = itemView.findViewById(R.id.textBodyNote)
        private var textEditDate: TextView = itemView.findViewById(R.id.textEditDate)


        // привязка модели и UI
        fun bindElements(note: NoteModel, action: ItemClickListener){


            if (note.title != null && note.title != ""){
                textTitleNote.text = note.title
            } else{
                textTitleNote.visibility = View.GONE
            }

            textBodyNote.text = note.text
            textEditDate.text = note.date

            itemView.setOnClickListener{ action.onItemClick(note = note) }
        }
    }


        //listener interface
        interface ItemClickListener{

         fun onItemClick(note: NoteModel)
         fun onLongItemClick(id: Int)
        }

}

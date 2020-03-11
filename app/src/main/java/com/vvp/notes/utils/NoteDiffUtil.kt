package com.vvp.notes.utils

import androidx.recyclerview.widget.DiffUtil
import com.vvp.notes.repository.NoteModel

class NoteDiffUtil  (private val oldList: ArrayList<NoteModel>, private val newList: List<NoteModel>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
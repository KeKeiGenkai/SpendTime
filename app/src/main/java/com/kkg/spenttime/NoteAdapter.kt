package com.kkg.spenttime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kkg.spenttime.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.titleTextView.text = currentNote.title
        holder.tagsTextView.text = currentNote.tags
        holder.contentTextView.text = currentNote.content
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun addNote(note: Note) {
        notes.add(note)
        notifyDataSetChanged()
    }

    fun updateData(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}

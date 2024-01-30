package com.kkg.spenttime
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kkg.spenttime.databinding.ItemNoteBinding

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemNoteBinding.bind(itemView)

    val titleTextView: TextView = binding.titleTextView
    val tagsTextView: TextView = binding.tagsTextView
    val contentTextView: TextView = binding.contentTextView
}
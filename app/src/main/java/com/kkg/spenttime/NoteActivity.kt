package com.kkg.spenttime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kkg.spenttime.databinding.ActivityNoteBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveNoteButton.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.titleEditText.text.toString()
        val content = binding.noteEditText.text.toString()

        val chipGroup: ChipGroup = findViewById(R.id.tagsChipGroup)
        val selectedTags = mutableListOf<String>()

        for (i in 0 until chipGroup.childCount) {
            val chip: Chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                selectedTags.add(chip.text.toString())
            }
        }

        val note = Note(title, selectedTags.joinToString(", "), content)

        saveNoteToSharedPreferences(note)

        finish()
    }

    private fun saveNoteToSharedPreferences(note: Note) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("notes_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val currentNotesString = sharedPreferences.getString("notes", null)
        val currentNotesType: Type = object : TypeToken<List<Note>>() {}.type
        val currentNotes: MutableList<Note> = if (currentNotesString != null) {
            Gson().fromJson(currentNotesString, currentNotesType)
        } else {
            mutableListOf()
        }

        currentNotes.add(note)

        val updatedNotesString = Gson().toJson(currentNotes)
        editor.putString("notes", updatedNotesString)
        editor.apply()
    }
}
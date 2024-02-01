package com.kkg.spenttime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kkg.spenttime.databinding.ActivityNoteBinding
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.activity_note.view.*
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
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
        val title = titleEditText.text.toString()
        val content = noteEditText.text.toString()

        // Получение выбранных тегов
        val chipGroup: ChipGroup = findViewById(R.id.tagsChipGroup)
        val selectedTags = mutableListOf<String>()

        for (i in 0 until chipGroup.childCount) {
            val chip: Chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                selectedTags.add(chip.text.toString())
            }
        }

        // Создание объекта заметки
        val note = Note(title, selectedTags.joinToString(", "), content)

        // Сохранение заметки в SharedPreferences
        saveNoteToSharedPreferences(note)

        // Завершение активности
        finish()
    }
    private fun saveNoteToSharedPreferences(note: Note) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("notes_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Получаем текущий список заметок
        val currentNotesString = sharedPreferences.getString("notes", null)
        val currentNotesType: Type = object : TypeToken<List<Note>>() {}.type
        val currentNotes: MutableList<Note> = if (currentNotesString != null) {
            Gson().fromJson(currentNotesString, currentNotesType)
        } else {
            mutableListOf()
        }

        // Добавляем новую заметку
        currentNotes.add(note)

        // Сохраняем обновленный список заметок
        val updatedNotesString = Gson().toJson(currentNotes)
        editor.putString("notes", updatedNotesString)
        editor.apply()
    }
}
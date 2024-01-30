package com.kkg.spenttime

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.kkg.spenttime.databinding.ActivityNoteBinding

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
        val tags = binding.tagsEditText.text.toString()
        val content = binding.noteEditText.text.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            val note = Note(title, tags, content)

            val sharedPreferences: SharedPreferences =
                getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            val noteJson = Gson().toJson(note)

            // Используйте уникальный ключ для каждой заметки, например, текущее время в миллисекундах
            val key = System.currentTimeMillis().toString()

            editor.putString(key, noteJson)
            editor.apply()

            setResult(RESULT_OK)
            finish()
        }
    }
}

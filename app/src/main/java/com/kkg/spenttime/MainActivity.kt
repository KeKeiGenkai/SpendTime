package com.kkg.spenttime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.kkg.spenttime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter


    private fun displaySavedNotes() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("notes_prefs", MODE_PRIVATE)

        val allNotes = sharedPreferences.all

        for (entry in allNotes.entries) {
            val noteJson = entry.value as String
            val note = Gson().fromJson(noteJson, Note::class.java)
            noteAdapter.addNote(note)
        }
    }

    private fun updateRecyclerView() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)

        val allNotes = sharedPreferences.all
        val updatedNotes = mutableListOf<Note>()

        for (entry in allNotes.entries) {
            val noteJson = entry.value as String
            val note = Gson().fromJson(noteJson, Note::class.java)
            updatedNotes.add(note)
        }

        noteAdapter.updateData(updatedNotes)
    }

    private val CREATE_NOTE_REQUEST = 1 // Уникальный код запроса

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }

        binding.createButton.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivityForResult(intent, CREATE_NOTE_REQUEST)
        }

        displaySavedNotes()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NOTE_REQUEST && resultCode == RESULT_OK) {
            // Здесь можно обновить список заметок после возвращения из NoteActivity
            updateRecyclerView()
        }
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerView()
    }
}

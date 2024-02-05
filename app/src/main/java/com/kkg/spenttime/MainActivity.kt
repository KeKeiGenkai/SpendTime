package com.kkg.spenttime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kkg.spenttime.databinding.ActivityMainBinding
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }

        displaySavedNotes()
    }

    private fun displaySavedNotes() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)

        val allNotes = sharedPreferences.all

        val notesList = mutableListOf<Note>()
        for (entry in allNotes.entries) {
            val noteJson = entry.value as String
            val note = Gson().fromJson(noteJson, Note::class.java)
            notesList.add(note)
        }

        noteAdapter.clearNotes()
        noteAdapter.addAllNotes(notesList)
    }
}
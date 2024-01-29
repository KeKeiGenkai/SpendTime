package com.kkg.spenttime

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton = findViewById<Button>(R.id.myButton)
        myButton.setOnClickListener {
            // Создаем Intent для перехода к новой активности
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }
}
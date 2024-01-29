package com.kkg.spenttime
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val saveNoteButton = findViewById<Button>(R.id.saveNoteButton)
        saveNoteButton.setOnClickListener {
            // Получаем значения
            val title = titleEditText.text.toString()
            val tags = tagsEditText.text.toString()
            val noteText = noteEditText.text.toString()

            // TODO: Сохранение заметки в базе данных или другом хранилище

            // Закрываем текущую активность и возвращаемся к предыдущей
            finish()
        }
    }
}

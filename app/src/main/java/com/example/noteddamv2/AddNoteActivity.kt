package com.example.noteddamv2

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var notesDBHelper: NotesDBHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        notesDBHelper = NotesDBHelper(this)

        var edit_text_message:EditText = findViewById(R.id.edit_text_message)
        var save_button:Button = findViewById(R.id.save_button)

        save_button.setOnClickListener {
            // pick date and hour

            val dateformat: String = "dd/MM/yyyy" //In which you need put here
            val hourformat: String = "HH:mm" //In which you need put here
            val sdfdate = SimpleDateFormat(dateformat, Locale.UK)
            val sdfhour = SimpleDateFormat(hourformat, Locale.UK)

            val current = Calendar.getInstance()
            val formatted_date = sdfdate.format(current.time)

//            val formatter_hour = DateTimeFormatter.ofPattern("HH:mm") // hours and minuts.
            val formatted_hour = sdfhour.format(current.time)

            var note_date = formatted_date.toString()
            var note_hour = formatted_hour.toString()
            // Pick message from .xml
            var note_message1: EditText = findViewById(R.id.edit_text_message)
            var note_message = note_message1.text.toString()


            // Am pus noteid = 0 pt ca este cu AUTOINCREMENT, si vreau sa folosesc id-ul.
            var result = notesDBHelper.insertNote(NoteModel(noteid = 0, note_date = note_date,note_hour = note_hour, note_message=note_message))

            var text_edit_text : EditText = findViewById(R.id.edit_text_message)
            text_edit_text.setText(" ")
            text_edit_text.hint = "Scrie notita!"
            Toast.makeText(this, "Notita a fost salvata.", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }
}

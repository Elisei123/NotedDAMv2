package com.example.noteddamv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import com.example.notes_app_dam.adapters.AttractionsAdapter
import com.example.notes_app_dam.adapters.NoteAdapter
import java.util.ArrayList

class VeziNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vezi_note)

        var notesDBHelper = NotesDBHelper(this)

        var SearchViewNote:SearchView = findViewById(R.id.SearchNote)
        var ListViewNote:ListView = findViewById(R.id.ListViewNote)

        SearchViewNote.setOnSearchClickListener {

            var TextDeCautat = SearchViewNote.query
            var notes = notesDBHelper.readAllNotes()
            var notesfiltrat: ArrayList<NoteModel> = ArrayList()
            for (item: NoteModel in notes) {
                //cauta in ce ai nevoie
            }
//            ListViewNote.setAdapter(
//                NoteAdapter(
//                    this,
//                    notesfiltrat
//                )
//            )
        }

        var notes = notesDBHelper.readAllNotes()
        ListViewNote.setAdapter(
            AttractionsAdapter(
                notes,this
            )
        )

    }
}

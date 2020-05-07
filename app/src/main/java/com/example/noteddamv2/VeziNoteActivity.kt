package com.example.noteddamv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import android.widget.SearchView
import com.example.notes_app_dam.adapters.AttractionsAdapter
import java.util.ArrayList

class VeziNoteActivity : AppCompatActivity() {

    lateinit var notesDBHelper:NotesDBHelper
    lateinit var ListViewNote:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vezi_note)

        notesDBHelper = NotesDBHelper(this)

        var SearchViewNote:SearchView = findViewById(R.id.SearchNote)
        ListViewNote = findViewById(R.id.ListViewNote)

        SearchViewNote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                var TextDeCautat = query.toString()

                if (TextUtils.isEmpty(TextDeCautat))
                {
                    RefreshData()
                }else{
                    var notes = notesDBHelper.readAllNotes()
                    var notesfiltrat: ArrayList<NoteModel> = ArrayList()
                    for (item: NoteModel in notes) {
                        if (item.note_message.contains(TextDeCautat)||item.note_hour.contains(TextDeCautat)||item.note_date.contains(TextDeCautat))
                            notesfiltrat.add(item)
                    }
                    ListViewNote.setAdapter(
                        AttractionsAdapter(
                            notesfiltrat,this@VeziNoteActivity
                        )
                    )
                }
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        RefreshData()
    }

    fun RefreshData()
    {
        var notes = notesDBHelper.readAllNotes()
        ListViewNote.setAdapter(
            AttractionsAdapter(
                notes,this
            )
        )
    }

}

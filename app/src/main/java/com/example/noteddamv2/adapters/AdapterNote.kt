package com.example.notes_app_dam.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.noteddamv2.*
import java.text.SimpleDateFormat
import java.util.*


class AttractionsAdapter(items: ArrayList<NoteModel>, ctx: Context) :
    ArrayAdapter<NoteModel>(ctx, R.layout.element_lista_note, items) {
    lateinit var notesDBHelper: NotesDBHelper

    //view holder is used to prevent findViewById calls
    private class AttractionItemViewHolder {
        internal var textnota: AppCompatTextView? = null
        internal var datanota: AppCompatTextView? = null
        internal var editeaza: AppCompatButton? = null
        internal var sterge: AppCompatButton? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val viewHolder: AttractionItemViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.element_lista_note, viewGroup, false)

            viewHolder = AttractionItemViewHolder()
            viewHolder.textnota = view!!.findViewById<View>(R.id.TextNota) as AppCompatTextView
            viewHolder.datanota = view.findViewById<View>(R.id.DataNota) as AppCompatTextView
            viewHolder.editeaza = view.findViewById<View>(R.id.ButtonEdit) as AppCompatButton
            viewHolder.sterge = view.findViewById<View>(R.id.ButtonDelete) as AppCompatButton
            view.tag = viewHolder;
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as AttractionItemViewHolder
        }
        notesDBHelper = NotesDBHelper(context)

        val attraction = getItem(i)
        viewHolder.textnota!!.text = attraction!!.note_message
        viewHolder.datanota!!.text = attraction.note_date+" "+attraction.note_hour

        viewHolder.editeaza!!.setOnClickListener {
            val intent = Intent(context, EditNoteActivity::class.java).apply {
                putExtra("noteMessage",attraction.note_message)
                putExtra("id_item",attraction.noteid)
            }
            (context as VeziNoteActivity).startActivity(intent)
        }
        viewHolder.sterge!!.setOnClickListener {
            notesDBHelper.deleteUser(attraction.noteid)
            Toast.makeText(context, "Nota a fost stearsa!",
                Toast.LENGTH_SHORT).show()
            (context as VeziNoteActivity).RefreshData();
        }

        return view
    }
}
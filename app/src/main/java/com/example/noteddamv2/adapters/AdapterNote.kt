package com.example.notes_app_dam.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.startActivity
import com.example.noteddamv2.EditNoteActivity
import com.example.noteddamv2.NoteModel
import com.example.noteddamv2.NotesDBHelper
import com.example.noteddamv2.R


class NoteAdapter(
    context: Context,
    products: List<NoteModel?>
) :
    ArrayAdapter<NoteModel?>(context, 0, products) {
    fun getView(convertView: View, position: Int, parent: ViewGroup): View {

        // Get the data item for this position
        var convertView: View = convertView
        val nota: NoteModel? = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.element_lista_note, parent, false)
        }
        val TextNota: AppCompatTextView =
            convertView.findViewById(R.id.TextNota)
        val DataNota: AppCompatTextView = convertView.findViewById(R.id.DataNota)
        val ButtonEdit: AppCompatButton = convertView.findViewById(R.id.ButtonEdit)
        val ButtonDelete: AppCompatButton = convertView.findViewById(R.id.ButtonDelete)


        if (nota != null) {
            //region Data Load
            TextNota.setText(nota.note_message)
            DataNota.setText(nota.note_date+" "+nota.note_hour)

            ButtonEdit.setOnClickListener{
                val intent = Intent(context, EditNoteActivity::class.java).apply {
                    putExtra("noteMessage",  nota.note_message)
                    putExtra("id_item", nota.noteid)
                }
                context.startActivity(intent)
            }

            ButtonDelete.setOnClickListener{
                var notesDBHelper = NotesDBHelper(context)
                notesDBHelper.deleteUser(nota.noteid)
                Toast.makeText(context, "Notita a fost stearsa.", Toast.LENGTH_SHORT).show()
            }

        }
        return convertView
    }
}

class AttractionsAdapter(items: ArrayList<NoteModel>, ctx: Context) :
    ArrayAdapter<NoteModel>(ctx, R.layout.element_lista_note, items) {

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
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as AttractionItemViewHolder
        }

        val attraction = getItem(i)
        viewHolder.textnota!!.text = attraction!!.note_message
        viewHolder.datanota!!.text = attraction.note_date+" "+attraction.note_hour

        //shows how to handle events of views of items
        viewHolder.editeaza!!.setOnClickListener {
            Toast.makeText(context, "Editeazama",
                Toast.LENGTH_SHORT).show()
        }
        viewHolder.sterge!!.setOnClickListener {
            Toast.makeText(context, "Stergema",
                Toast.LENGTH_SHORT).show()
        }


        view.tag = viewHolder

        return view
    }
}
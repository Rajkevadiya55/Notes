package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes.Database.NoteDB
import com.example.notes.Database.NoteEntity
import org.w3c.dom.Text

class NotesAdapter(list: List<NoteEntity>) : Adapter<NotesAdapter.NotesHolder>() {
    var list = list
    lateinit var db: NoteDB
    lateinit var context: Context


    class NotesHolder(itemView: View) : ViewHolder(itemView) {
        var txtTitle = itemView.findViewById<TextView>(R.id.txttitle)
        var txtNote = itemView.findViewById<TextView>(R.id.txtnote)
        var carNote = itemView.findViewById<CardView>(R.id.cardnote)
        var imgpin = itemView.findViewById<ImageView>(R.id.imgpin1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NotesHolder {
        context = parent.context

        return NotesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {

        db = NoteDB.getInstances(context)

        holder.txtTitle.text = list.get(position).title
        holder.txtNote.text = list.get(position).note
        holder.carNote.setCardBackgroundColor(list.get(position).color)


        if (list.get(position).pinned) {
            holder.imgpin.setImageResource(R.drawable.pin1)
        } else {
            holder.imgpin.setImageResource(R.drawable.pin2)
        }

        holder.imgpin.setOnClickListener {

            if (list.get(position).pinned) {
                var data = NoteEntity(
                    list.get(position).title,
                    list.get(position).note,
                    list.get(position).date,
                    list.get(position).color,
                    false
                )
                data.id = list.get(position).id
                db.notes().updateNote(data)

            } else {

                var data = NoteEntity(
                    list.get(position).title,
                    list.get(position).note,
                    list.get(position).date,
                    list.get(position).color,
                    true
                )
                data.id = list.get(position).id
                db.notes().updateNote(data)
            }
            MainActivity.update()
        }
    }

    override fun getItemCount(): Int {
        return list.size

    }
}

package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.Database.NoteDB
import com.example.notes.Database.NoteEntity
import com.example.notes.databinding.ActivityAddNoteBinding
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var binding:ActivityMainBinding
        lateinit var db:NoteDB
        var notelist=ArrayList<NoteEntity>()

        fun update() {
            var list = db.notes().getNote()
            list = list.reversed()
            notelist.clear()
            for (l in list) {
                if (l.pinned) {
                    notelist.add(l)
                }
            }
            for (l in list) {
                if (!l.pinned) {
                    notelist.add(l)
                }
    }
            binding.recycle.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.recycle.adapter = NotesAdapter(notelist)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)

         db = NoteDB.getInstances(this)

        binding.btnAdd.setOnClickListener {
             startActivity(Intent(this@MainActivity,AddNoteActivity::class.java))
        }

        update()
    }


    override fun onResume() {
        super.onResume()
        update()
    }
}
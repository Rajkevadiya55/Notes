package com.example.notes

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.notes.Database.NoteDB
import com.example.notes.Database.NoteEntity
import com.example.notes.databinding.ActivityAddNoteBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {


    lateinit var binding: ActivityAddNoteBinding
    var selectColar = 0

    lateinit var db: NoteDB


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDB.getInstances(this)

        binding.btnSave.setOnClickListener {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss a")
            val current = LocalDateTime.now().format(formatter)

            var note = NoteEntity(
                binding.edttitle.text.toString(),
                binding.edtnote.text.toString(), current, selectColar, false
            )
            db.notes().addNote(note)
            finish()
        }

        binding.cardColors.setOnClickListener {

            MaterialColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setColorSwatch(ColorSwatch._300)
                .setColorListener { color, colorHex ->
                    binding.cardColors.setBackgroundColor(color)
                    selectColar=color
                }
                .show()
        }
    }
}

package com.example.notes.Database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun addNote(note:NoteEntity)

    @Query("SELECT * FROM Note")
    fun  getNote():List<NoteEntity>

    @Update
    fun updateNote(note: NoteEntity)

}
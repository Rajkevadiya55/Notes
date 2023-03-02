package com.example.notes.Database

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    companion object {

        fun getInstances(context: Context): NoteDB {
            var db = Room.databaseBuilder(context, NoteDB::class.java, "Note")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

    abstract fun notes(): NoteDao
}
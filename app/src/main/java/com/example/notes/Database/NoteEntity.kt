package com.example.notes.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Note")

data class NoteEntity(

    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "color") var color: Int,
    @ColumnInfo(name = "pinned") var pinned: Boolean,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
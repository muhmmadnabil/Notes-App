package com.muhmmadnabil.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.muhmmadnabil.notesapp.model.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Update
    suspend fun update(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<Notes>>
}
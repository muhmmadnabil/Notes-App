package com.muhmmadnabil.notesapp.data

import com.muhmmadnabil.notesapp.model.Notes

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes=notesDao.getAllNotes()

    suspend fun insert(notes: Notes){
        notesDao.insert(notes)
    }

    suspend fun update(notes: Notes){
        notesDao.update(notes)
    }

    suspend fun delete(notes: Notes){
        notesDao.delete(notes)
    }

}
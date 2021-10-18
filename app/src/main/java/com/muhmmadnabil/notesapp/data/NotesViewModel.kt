package com.muhmmadnabil.notesapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muhmmadnabil.notesapp.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) :AndroidViewModel(application){
    val allNotes: LiveData<List<Notes>>
    val repository: NotesRepository

    init{
        val dao= NotesDatabase.getDatabase(application).getNotesDao()
        repository= NotesRepository(dao)
        allNotes=repository.allNotes
    }

    fun deleteNotes(notes: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }

    fun updateNotes(notes: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(notes)
    }

    fun addNotes(notes: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }
}
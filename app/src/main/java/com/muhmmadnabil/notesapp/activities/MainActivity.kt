package com.muhmmadnabil.notesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhmmadnabil.notesapp.*
import com.muhmmadnabil.notesapp.model.Notes
import com.muhmmadnabil.notesapp.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_notes.layoutManager=LinearLayoutManager(this)
        val notesAdapter= NotesAdapter(this,this,this)
        rv_notes.adapter=notesAdapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NotesViewModel::class.java)
        viewModel.allNotes.observe(this,{ list->
            list?.let {
                notesAdapter.updateList(it)
            }
        })
        fab_add.setOnClickListener {
            val intent=Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDeleteIconClick(notes: Notes) {
        viewModel.deleteNotes(notes)
        Toast.makeText(this,"${notes.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(notes: Notes) {
        val intent=Intent(this, NoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",notes.noteTitle)
        intent.putExtra("noteDescription",notes.noteDescription)
        intent.putExtra("noteId",notes.id)
        startActivity(intent)
    }
}
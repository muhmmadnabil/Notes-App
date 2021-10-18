package com.muhmmadnabil.notesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.muhmmadnabil.notesapp.R
import com.muhmmadnabil.notesapp.model.Notes
import com.muhmmadnabil.notesapp.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel
    private var noteId=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NotesViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDes=intent.getStringExtra("noteDescription")
            noteId=intent.getIntExtra("noteId",-1)
            btn_update.text="Update Note"
            et_edit_title.setText(noteTitle)
            et_edit_description.setText(noteDes)
        }

        btn_update.setOnClickListener {
            val noteTitle=et_edit_title.text.toString()
            val noteDes=et_edit_description.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDes.isNotEmpty()){
                    val sdf= SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate=sdf.format(Date())
                    val updateNote= Notes(noteTitle,noteDes,currentDate)
                    updateNote.id=noteId
                    viewModel.updateNotes(updateNote)
                    Toast.makeText(this,"Note Updated ...",Toast.LENGTH_LONG).show()
                }
            }else{
                if(noteTitle.isNotEmpty() && noteDes.isNotEmpty()){
                    val sdf= SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate=sdf.format(Date())
                    viewModel.addNotes(Notes(noteTitle,noteDes,currentDate))
                    Toast.makeText(this,"Note Added ...",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }

    override fun onBackPressed() {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
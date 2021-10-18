package com.muhmmadnabil.notesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhmmadnabil.notesapp.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        fad_start_add.setOnClickListener {
            val intent= Intent(this, NoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}
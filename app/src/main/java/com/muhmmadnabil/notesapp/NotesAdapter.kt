package com.muhmmadnabil.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhmmadnabil.notesapp.model.Notes

class NotesAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
) :RecyclerView.Adapter<NotesAdapter.ViewHolder>(){
    val allNotes=ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text=allNotes[position].noteTitle
        holder.noteTime.text="Last Updated : "+allNotes[position].timeStamp
        holder.noteDelete.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val noteTitle=itemView.findViewById<TextView>(R.id.tv_title)
        val noteTime=itemView.findViewById<TextView>(R.id.tv_time)
        val noteDelete=itemView.findViewById<ImageView>(R.id.iv_delete)
    }

    fun updateList(new_list:List<Notes>){
        allNotes.clear()
        allNotes.addAll(new_list)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(notes: Notes)
}

interface NoteClickInterface {
    fun onNoteClick(notes: Notes)
}
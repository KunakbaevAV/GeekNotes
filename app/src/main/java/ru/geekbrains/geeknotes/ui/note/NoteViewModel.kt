package ru.geekbrains.geeknotes.ui.note

import androidx.lifecycle.ViewModel
import ru.geekbrains.geeknotes.data.Repository
import ru.geekbrains.geeknotes.data.model.Note

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}
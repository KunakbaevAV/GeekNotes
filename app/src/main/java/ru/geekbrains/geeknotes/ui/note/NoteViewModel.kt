package ru.geekbrains.geeknotes.ui.note

import androidx.lifecycle.Observer
import ru.geekbrains.geeknotes.data.Repository
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.data.model.NoteResult
import ru.geekbrains.geeknotes.data.model.NoteResult.Error
import ru.geekbrains.geeknotes.ui.base.BaseViewModel

class NoteViewModel(val repository: Repository = Repository) : BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever(object : Observer<NoteResult> {
            override fun onChanged(t: NoteResult?) {
                if (t == null) return

                when (t) {
                    is NoteResult.Success<*> ->
                        viewStateLiveData.value = NoteViewState(note = t.data as? Note)
                    is Error ->
                        viewStateLiveData.value = NoteViewState(error = t.error)
                }
            }
        })
    }
}
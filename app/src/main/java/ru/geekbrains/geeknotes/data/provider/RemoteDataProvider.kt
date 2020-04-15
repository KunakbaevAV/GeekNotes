package ru.geekbrains.geeknotes.data.provider

import androidx.lifecycle.LiveData
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
}
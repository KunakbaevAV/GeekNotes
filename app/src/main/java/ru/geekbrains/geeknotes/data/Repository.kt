package ru.geekbrains.geeknotes.data

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.geeknotes.data.model.Color
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.data.provider.FireStoreProvider
import ru.geekbrains.geeknotes.data.provider.RemoteDataProvider
import java.util.*

object Repository {
    private val notesLiveData = MutableLiveData<List<Note>>()
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    private val notes: MutableList<Note> = mutableListOf(
        Note(UUID.randomUUID().toString(),"Моя заметка 1", "Текст заметки", Color.WHITE),
        Note(UUID.randomUUID().toString(),"Моя заметка 2", "Текст заметки", Color.BLUE),
        Note(UUID.randomUUID().toString(),"Моя заметка 3", "Текст заметки", Color.GREEN),
        Note(UUID.randomUUID().toString(),"Моя заметка 4", "Текст заметки", Color.PINK),
        Note(UUID.randomUUID().toString(),"Моя заметка 5", "Текст заметки", Color.RED),
        Note(UUID.randomUUID().toString(),"Моя заметка 6", "Текст заметки", Color.YELLOW),
        Note(UUID.randomUUID().toString(),"Моя заметка 7", "Текст заметки", Color.WHITE),
        Note(UUID.randomUUID().toString(),"Моя заметка 8", "Текст заметки", Color.BLUE),
        Note(UUID.randomUUID().toString(),"Моя заметка 9", "Текст заметки", Color.GREEN),
        Note(UUID.randomUUID().toString(),"Моя заметка 10", "Текст заметки", Color.PINK),
        Note(UUID.randomUUID().toString(),"Моя заметка 11", "Текст заметки", Color.YELLOW),
        Note(UUID.randomUUID().toString(),"Моя заметка 12", "Текст заметки", Color.WHITE),
        Note(UUID.randomUUID().toString(),"Моя заметка 13", "Текст заметки", Color.BLUE),
        Note(UUID.randomUUID().toString(),"Моя заметка 14", "Текст заметки", Color.PINK),
        Note(UUID.randomUUID().toString(),"Моя заметка 15", "Текст заметки", Color.YELLOW),
        Note(UUID.randomUUID().toString(),"Моя заметка 16", "Текст заметки", Color.BLUE)
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()

    private fun addOrReplace(note: Note) {

        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes.set(i, note)
                return
            }
        }
        notes.add(note)
    }
}
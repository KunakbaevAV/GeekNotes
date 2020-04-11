package ru.geekbrains.geeknotes.data

import ru.geekbrains.geeknotes.data.model.Color
import ru.geekbrains.geeknotes.data.model.Note
import java.util.*

object Repository {
    val notes = listOf(
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
}
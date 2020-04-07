package ru.geekbrains.geeknotes.data

import ru.geekbrains.geeknotes.data.model.Note

object Repository {
    private val notes = listOf(
        Note("Моя заметка 1", "Текст заметки", Colors.COLOR_1),
        Note("Моя заметка 2", "Текст заметки", Colors.COLOR_2),
        Note("Моя заметка 3", "Текст заметки", Colors.COLOR_3),
        Note("Моя заметка 4", "Текст заметки", Colors.COLOR_4),
        Note("Моя заметка 5", "Текст заметки", Colors.COLOR_5),
        Note("Моя заметка 6", "Текст заметки", Colors.COLOR_6),
        Note("Моя заметка 7", "Текст заметки", Colors.COLOR_7)
    )

    fun getNotes() = notes

}
package ru.geekbrains.geeknotes.ui.note

import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) :
    BaseViewState<Note?>(note, error)
package ru.geekbrains.geeknotes.ui

import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.ui.base.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error){
}
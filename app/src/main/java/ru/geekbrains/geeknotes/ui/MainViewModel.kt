package ru.geekbrains.geeknotes.ui

import androidx.lifecycle.Observer
import ru.geekbrains.geeknotes.data.Repository
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.data.model.NoteResult
import ru.geekbrains.geeknotes.ui.base.BaseViewModel
import ru.geekbrains.geeknotes.data.model.NoteResult.Error
import ru.geekbrains.geeknotes.data.model.NoteResult.Success

@Suppress("UNCHECKED_CAST")
class MainViewModel(repository: Repository = Repository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult>{
    override fun onChanged(t: NoteResult?) {
        if (t == null) return

        when(t) {
            is Success<*> -> {
                viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
            }
            is Error -> {
                viewStateLiveData.value = MainViewState(error = t.error)
            }
        }
    }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}
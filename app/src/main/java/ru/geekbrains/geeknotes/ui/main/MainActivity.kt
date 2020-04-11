package ru.geekbrains.geeknotes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.geeknotes.R
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.ui.MainAdapter
import ru.geekbrains.geeknotes.ui.MainViewModel
import ru.geekbrains.geeknotes.ui.MainViewState
import ru.geekbrains.geeknotes.ui.note.NoteActivity

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener{
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState>{t ->
            t?.let { adapter.notes = it.notes }
        })

        fab.setOnClickListener { openNoteScreen(null) }
    }

    private fun openNoteScreen(note: Note?){
        val intent = NoteActivity.getStartIntent(this, note)
        startActivity(intent)
    }
}

package ru.geekbrains.geeknotes.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.item_note.*
import ru.geekbrains.geeknotes.R
import ru.geekbrains.geeknotes.data.format
import ru.geekbrains.geeknotes.data.getColorInt
import ru.geekbrains.geeknotes.data.model.Color
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.ui.base.BaseActivity
import java.util.*

private const val SAVE_DELAY = 2000L

class NoteActivity : BaseActivity<Note?, NoteViewState>() {

    override val viewModel: NoteViewModel by lazy {
        ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override val layoutRes: Int = R.layout.activity_note

    private var note: Note? = null

    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"

        fun getStartIntent(context: Context, note: String?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = intent.getStringExtra(EXTRA_NOTE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        noteId?.let {
            viewModel.loadNote(it)
        }

        if (noteId == null) supportActionBar?.title = getString(R.string.new_note_title)

        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)
    }

    private fun initView() {

        note?.run {
            supportActionBar?.title = lastChanged.format()

            titleEt.setText(title)
            bodyEt.setText(note)

            toolbar.setBackgroundColor(color.getColorInt(this@NoteActivity))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // not used
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // not used
        }
    }


    private fun triggerSaveNote() {
        if (titleEt.text == null || titleEt.text!!.length < 3) return

        Handler().postDelayed({
            note = note?.copy(
                title = titleEt.text.toString(),
                note = bodyEt.text.toString(),
                lastChanged = Date()
            )

            if (note != null) viewModel.saveChanges(note!!)
        }, SAVE_DELAY)
    }


    private fun createNewNote(): Note = Note(
        UUID.randomUUID().toString(),
        titleEt.text.toString(),
        bodyEt.text.toString()
    )

    override fun renderData(data: Note?) {
        this.note = data
        initView()
    }
}

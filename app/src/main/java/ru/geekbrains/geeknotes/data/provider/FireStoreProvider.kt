package ru.geekbrains.geeknotes.data.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.*
import ru.geekbrains.geeknotes.data.model.Note
import ru.geekbrains.geeknotes.data.model.NoteResult
import java.lang.Exception

private const val NOTES_COLLECTION = "notes"

class FireStoreProvider : RemoteDataProvider {

    private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    private val db = FirebaseFirestore.getInstance()
    private val notesReference = db.collection(NOTES_COLLECTION)

    override fun subscribeToAllNotes(): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            notesReference.addSnapshotListener { snapshot, e ->
                value = e?.let { NoteResult.Error(it) }
                    ?: snapshot?.let {
                        val notes = it.documents.map { it.toObject(Note::class.java) }
                        NoteResult.Success(notes)
                    }
            }
        }

    override fun getNoteById(id: String): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            notesReference.document(id).get()
                .addOnSuccessListener { value = NoteResult.Success(it?.toObject(Note::class.java)) }
                .addOnFailureListener { value = NoteResult.Error(it) }
        }

    override fun saveNote(note: Note): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            notesReference.document(note.id)
                .set(note).addOnSuccessListener {
                    Log.d(TAG, "Note $note is saved")
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    Log.d(TAG, "Error saving note $note, message: ${it.message}")
                    value = NoteResult.Error(it)
                }
        }
}
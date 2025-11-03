package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {
    private static final String TAG = "DeleteNoteActivity";
    private Spinner spinnerNotes;
    private Button btnDelete;
    private NoteManager noteManager;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);
        btnDelete = findViewById(R.id.btnDelete);
        noteManager = new NoteManager(this);

        notesList = noteManager.getAllNotesTitles();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotes.setAdapter(adapter);

        btnDelete.setOnClickListener(v -> {
            Log.d(TAG, "Delete button clicked");
            String selected = (String) spinnerNotes.getSelectedItem();
            if (selected == null) {
                Toast.makeText(this, "Tai ką tu trinsi?", Toast.LENGTH_SHORT).show();
                return;
            }

            noteManager.deleteNote(selected);
            Toast.makeText(this, "Sajonara, uŽraše", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

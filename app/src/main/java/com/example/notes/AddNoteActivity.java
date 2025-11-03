package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.util.Log;

public class AddNoteActivity extends AppCompatActivity {
    private static final String TAG = "AddNoteActivity";
    private EditText txtNoteName, txtNoteContent;
    private RadioButton radioSharedPref, radioFile;
    private Button btnSave;
    private NoteManager noteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Log.d(TAG, "onCreate() called");
        txtNoteName = findViewById(R.id.txtNoteName);
        txtNoteContent = findViewById(R.id.txtNoteContent);
        radioSharedPref = findViewById(R.id.radioSharedPref);
        radioFile = findViewById(R.id.radioFile);
        btnSave = findViewById(R.id.btnSave);
        noteManager = new NoteManager(this);

        btnSave.setOnClickListener(v -> {
            String name = txtNoteName.getText().toString().trim();
            String content = txtNoteContent.getText().toString().trim();
            Log.d(TAG, "Save button clicked");
            if (name.isEmpty() || content.isEmpty()) {
                Log.d(TAG, "Layout check: " + R.layout.activity_add_note);
                Log.d(TAG, "DEBUG: txtNoteName null? " + (txtNoteName == null));
                Log.d(TAG, "DEBUG: txtNoteContent null? " + (txtNoteContent == null));
                Toast.makeText(this, "ALIO! Abu turi būti užpildyti", Toast.LENGTH_SHORT).show();
                return;
            }

            if (radioSharedPref.isChecked()) {
                noteManager.saveNoteSharedPref(name, content);
            } else if (radioFile.isChecked()) {
                noteManager.saveNoteFile(name, content);
            } else {
                Toast.makeText(this, "O tai kaip saugosi?", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Kuriam laikui išsaugojai", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

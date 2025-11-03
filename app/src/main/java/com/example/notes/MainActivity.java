package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listViewNotes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> notesList;
    private NoteManager noteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        noteManager = new NoteManager(this);
        notesList = noteManager.getAllNotesTitles();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        notesList.clear();
        notesList.addAll(noteManager.getAllNotesTitles());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu() called");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() called: " + item.getTitle());
        if (item.getItemId() == R.id.menu_add_note) {
            startActivity(new Intent(this, AddNoteActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu_delete_note) {
            startActivity(new Intent(this, DeleteNoteActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

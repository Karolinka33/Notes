package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class NoteManager {
    private static final String TAG = "NoteManager";
    private static final String PREF_NAME = "notes_prefs";
    private Context context;

    public NoteManager(Context context) {
        this.context = context;
    }

    public void saveNoteSharedPref(String name, String content) {
        Log.d(TAG, "saveNoteSharedPref() called for: " + name);
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(name, content).apply();
    }

    public void saveNoteFile(String name, String content) {
        Log.d(TAG, "saveNoteFile() called for: " + name);
        try (FileOutputStream fos = context.openFileOutput(name + ".txt", Context.MODE_PRIVATE)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
        }
    }

    public ArrayList<String> getAllNotesTitles() {
        Log.d(TAG, "getAllNotesTitles() called");
        ArrayList<String> titles = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Map<String, ?> all = prefs.getAll();
        titles.addAll(all.keySet());

        for (String filename : context.fileList()) {
            if (filename.endsWith(".txt")) {
                titles.add(filename.replace(".txt", ""));
            }
        }
        return titles;
    }

    public void deleteNote(String name) {
        Log.d(TAG, "deleteNote() called for: " + name);
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (prefs.contains(name)) prefs.edit().remove(name).apply();

        File file = new File(context.getFilesDir(), name + ".txt");
        if (file.exists()) file.delete();
    }
}

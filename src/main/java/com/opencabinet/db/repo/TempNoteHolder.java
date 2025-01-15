package com.opencabinet.db.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempNoteHolder {
    Map<String, String> notes = new HashMap<>();

    public TempNoteHolder() {

    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public void addNote(String title, String content) {
        notes.put(title, content);
    }

    public void removeNote(String title) {
        notes.remove(title);
    }
}

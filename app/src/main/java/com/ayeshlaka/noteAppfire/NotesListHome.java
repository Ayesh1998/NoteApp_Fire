package com.ayeshlaka.noteAppfire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesListHome extends AppCompatActivity {

    FloatingActionButton floatingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list_home);

        floatingBtn = (FloatingActionButton)findViewById(R.id.floatingActionButton2);

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(NotesListHome.this, com.ayeshlaka.noteAppfire.AddNote.class);
                startActivity(inte);
            }
        });
    }
}

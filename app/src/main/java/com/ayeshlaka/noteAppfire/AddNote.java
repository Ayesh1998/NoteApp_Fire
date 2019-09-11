package com.ayeshlaka.noteAppfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity {

    EditText title,content;
    Button createNote;
    String title1,content1;

    FirebaseAuth ffAuth;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = (EditText)findViewById(R.id.etNoteTitle);
        content = (EditText)findViewById(R.id.etNoteContent);
        createNote = (Button)findViewById(R.id.btCreateNote);

        ffAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("Notes").child(ffAuth.getCurrentUser().getUid());

        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title1 = title.getText().toString();
                content1 = content.getText().toString();

                DatabaseReference db2 = db.push();

                if (((TextUtils.isEmpty(title1) )&& (TextUtils.isEmpty(content1)))){
                    Toast.makeText(AddNote.this, "Fill Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    Map notesMapping = new HashMap();
                    notesMapping.put("title",title1);
                    notesMapping.put("content",content1);
                    notesMapping.put("time", ServerValue.TIMESTAMP);

                    db2.setValue(notesMapping).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AddNote.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(AddNote.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });
    }
}

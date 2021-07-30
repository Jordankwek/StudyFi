package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditActivity extends AppCompatActivity {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        EditText editText = (EditText) findViewById(R.id.editText);

        //get noteid which passed from main activity

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        //create edit text and display it

        if (noteID != -1) {

            editText.setText(NotesActivity.notes.get(noteID));
        }
        else {
            NotesActivity.notes.add("");
            noteID = NotesActivity.notes.size() -1;
            NotesActivity.arrayAdapter.notifyDataSetChanged();



        }

        //autosave the notes when edited

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

                NotesActivity.notes.set(noteID, String.valueOf(charSequence));
                NotesActivity.arrayAdapter.notifyDataSetChanged();

                // save notes

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sg.edu.np.mad.Studyfi", Context.MODE_PRIVATE);

                // create set to get string
                HashSet<String> set = new HashSet(NotesActivity.notes);

                //save it in SP

                sharedPreferences.edit().putStringSet("notes", set).apply();




            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }
}
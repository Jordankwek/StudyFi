package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        LinearLayout toDoFunc = findViewById(R.id.toDoFunc);
        LinearLayout studyNoteFunc = findViewById(R.id.studyNoteFunc);
        LinearLayout timerFunc = findViewById(R.id.timerFunc);
        LinearLayout radioFunc = findViewById(R.id.radioFunc);
        LinearLayout photoMathFunc = findViewById(R.id.photoMathFunc);
        LinearLayout dictionaryFunc = findViewById(R.id.dictionaryFunc);

        //Goes to To do activity page clicked
        toDoFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToDoActivity.class);
                startActivity(intent);
            }
        });

        //Goes to radio selection page when clicked
        radioFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectRadioActivity.class);
                startActivity(intent);
            }
        });
    }

}
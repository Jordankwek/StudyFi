package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        LinearLayout messageFunc = findViewById(R.id.messageFunc);

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

        //Goes to Timer page when clicked
        timerFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });

        messageFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Call function to start the notification timer

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit Application");
        builder.setMessage("Are you sure you want exit the app?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
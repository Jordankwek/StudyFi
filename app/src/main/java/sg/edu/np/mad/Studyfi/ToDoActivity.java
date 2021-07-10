package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {

    //Usage of database
    DatabaseHandler databaseHandler = new DatabaseHandler(this,null,null,1);
    ArrayList<ToDo> toDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        getSupportActionBar().hide();
        FloatingActionButton addTaskbutton = findViewById(R.id.addTaskbutton);

        //Get all the task from the database
        toDoList = databaseHandler.getAllTask();

        //Binding of data, viewholder and adapter to the rv
        RecyclerView todorv = findViewById(R.id.todorv);
        ToDoAdapter todoAdapter = new ToDoAdapter(toDoList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        todorv.setLayoutManager(linearLayoutManager);
        todorv.setAdapter(todoAdapter);

        //Add new task
        addTaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating an alert dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(ToDoActivity.this);
                final EditText input = new EditText(ToDoActivity.this);

                builder.setTitle("Add a task");
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                        String title = input.getText().toString();
                        if (title == null || title.trim().equals("")){
                            dialog.cancel();
                        }
                        ToDo task = new ToDo();
                        task.setTitle(title);
                        task.setStatus(0);
                        task.setUpdateDate(currentDate);
                        databaseHandler.addTask(task);
                        toDoList.add(task);
                        toDoList = databaseHandler.getAllTask();
                        todoAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });


    }
}
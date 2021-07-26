package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ToDoActivity extends AppCompatActivity {

    //Usage of database
    DatabaseHandler databaseHandler = new DatabaseHandler(this,null,null,1);
    ArrayList<ToDo> toDoList = new ArrayList<>();
    Dialog addDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        getSupportActionBar().hide();
        //Access the add button
        FloatingActionButton addTaskbutton = findViewById(R.id.addTaskbutton);

        addDialog = new Dialog(this);


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

                addDialog.setContentView(R.layout.add_task_dialog);
                addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button addBtn = addDialog.findViewById(R.id.addBtn);
                Button cancelBtn = addDialog.findViewById(R.id.cancelBtn);
                EditText editText = addDialog.findViewById(R.id.addTask);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                        String title = editText.getText().toString();
                        if (title==null || title.trim().equals(""))
                        {
                            addDialog.cancel();
                        }
                        else {
                            ToDo task = new ToDo();
                            task.setTitle(title);
                            task.setStatus(0);
                            task.setUpdateDate(currentDate);

                            databaseHandler.addTask(task);
                            toDoList.add(task);
                            todoAdapter.notifyDataSetChanged();
                            addDialog.dismiss();
                        }
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDialog.cancel();
                    }
                });

                addDialog.show();

            }
        });

    }
}
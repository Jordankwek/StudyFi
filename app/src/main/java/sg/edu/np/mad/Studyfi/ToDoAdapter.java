package sg.edu.np.mad.Studyfi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
    private ArrayList<ToDo> toDoList;
    private DatabaseHandler databaseHandler;

    public ToDoAdapter(ArrayList<ToDo> input, Context context) {
        toDoList = input;
        databaseHandler = new DatabaseHandler(context, null, null, 1);
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_item,
                parent,
                false
        );
        return new ToDoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        ToDo d = toDoList.get(position);
        holder.txtTitle.setText(d.getTitle());
        holder.checkBox.setChecked(convertToBool(d.getStatus()));

        //If user checks the checkbox, it will be saved to the database
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    databaseHandler.updateCheckBox(d.getId(), 1);

                } else {
                    Toast.makeText(context, "Undone", Toast.LENGTH_SHORT).show();
                    databaseHandler.updateCheckBox(d.getId(), 0);
                }
            }
        });

        //Editing of title
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final EditText input = new EditText(context);
                builder.setTitle("Edit task");
                builder.setView(input);
                builder.setCancelable(false);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                        String title = input.getText().toString();
                        if (title == null || title.trim().equals("")){
                            dialog.cancel();
                        }
                        databaseHandler.updateTask(d.getId(), title, currentDate);
                        toDoList = databaseHandler.getAllTask();
                        notifyDataSetChanged();
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

        holder.txtDate.setText((d.getUpdateDate()));

        //Deleting of task
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete task");
                builder.setMessage("Are you sure you want to remove this task?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHandler.deleteTask(d.getId());
                        toDoList = databaseHandler.getAllTask();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private boolean convertToBool(int i){
        if (i == 0){
            return false;
        }
        else{
            return true;
        }
    }


    @Override
    public int getItemCount() {
        return toDoList.size();
    }
}

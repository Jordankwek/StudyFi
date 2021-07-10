package sg.edu.np.mad.Studyfi;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ToDoViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle, txtDate;
    CheckBox checkBox;

    public ToDoViewHolder(View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.taskTitle);
        txtDate = itemView.findViewById(R.id.taskDate);
        checkBox = itemView.findViewById(R.id.checkBox);
    }
}

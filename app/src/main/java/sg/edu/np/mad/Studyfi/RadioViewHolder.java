package sg.edu.np.mad.Studyfi;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RadioViewHolder extends RecyclerView.ViewHolder {
    TextView radioTitle;
    //ImageView radioImage;

    public RadioViewHolder(View itemView) {
        super(itemView);
        radioTitle = itemView.findViewById(R.id.radioName);
        //radioImage = itemView.findViewById(R.id.radioName);
    }
}
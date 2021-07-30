package sg.edu.np.mad.Studyfi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//View holder for message sender
public class SenderViewHolder extends RecyclerView.ViewHolder {

    TextView txtMessage;

    public SenderViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMessage = itemView.findViewById(R.id.senderMsg);
    }
}

package sg.edu.np.mad.Studyfi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//Receiver view holder
public class ReceiverViewHolder extends RecyclerView.ViewHolder {

    TextView txtMessage;

    public ReceiverViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMessage = itemView.findViewById(R.id.receiverMsg);
    }
}

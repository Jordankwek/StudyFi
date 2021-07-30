package sg.edu.np.mad.Studyfi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Message> messageArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECEIVE = 2;

    //Constructor
    public ChatAdapter(Context context, ArrayList<Message> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflating different viewholders based on sender and receiver
        if(viewType==ITEM_SEND)
        {
            View item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.sender_item,
                    parent,
                    false
            );
            return new SenderViewHolder(item);
        }
        else
        {
            View item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.receiver_item,
                    parent,
                    false
            );
            return new ReceiverViewHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //Binding of data
        Message message = messageArrayList.get(position);
        if(holder.getClass()==SenderViewHolder.class)
        {
            SenderViewHolder senderViewHolder = (SenderViewHolder)holder;
            senderViewHolder.txtMessage.setText(message.getMessage());
        }
        else{
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder)holder;
            receiverViewHolder.txtMessage.setText(message.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageArrayList.get(position);
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSenderID()))
        {
            return ITEM_SEND;
        }
        else{
            return ITEM_RECEIVE;
        }
    }
}

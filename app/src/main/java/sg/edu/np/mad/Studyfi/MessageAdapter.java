package sg.edu.np.mad.Studyfi;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<User> messageList;
    private Context context;

    public MessageAdapter(ArrayList<User> input, Context context){
        messageList = input;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_item,
                parent,
                false
        );
        return new MessageViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        User user = messageList.get(position);
        holder.userName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

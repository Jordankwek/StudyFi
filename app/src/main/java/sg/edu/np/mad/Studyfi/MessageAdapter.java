package sg.edu.np.mad.Studyfi;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
        //Inflate message view holder
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_item,
                parent,
                false
        );
        return new MessageViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        //Binds user data to viewholder
        User user = messageList.get(position);
        holder.userName.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goes to chat activity to message
                Intent intent = new Intent(context, ChatActivity.class);
                //Get the name and uid of the person that you are messaging
                intent.putExtra("name", user.getName());
                intent.putExtra("uid", user.getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

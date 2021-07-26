package sg.edu.np.mad.Studyfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    //Receiver name
    TextView name;
    String receiverName, receiverId, senderID, senderRoom, receiverRoom;
    CardView sendBtn;
    EditText chatMessage;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    ArrayList<Message> messageArrayList;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();

        //Initialize arraylist
        ArrayList<Message> messageArrayList = new ArrayList<>();


        database = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/");
        firebaseAuth = FirebaseAuth.getInstance();

        //Receiver name
        receiverName = getIntent().getStringExtra("name");
        //Receiver id
        receiverId = getIntent().getStringExtra("uid");

        //Getting the Textview in the layout for the receiver's name
        name = findViewById(R.id.receiverName);
        //Setting receiver's name in the textview
        name.setText(receiverName);

        //Getting the send button
        sendBtn = findViewById(R.id.sendBtn);
        chatMessage = findViewById(R.id.chatMessage);

        senderID = firebaseAuth.getCurrentUser().getUid();
        /*
        senderRoom = senderID + receiverId;
        receiverRoom = receiverId + senderID;
         */

        DatabaseReference chatReference = database.getReference().child("chats");

        RecyclerView chatRv = findViewById(R.id.chatRv);
        chatAdapter = new ChatAdapter(ChatActivity.this, messageArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chatRv.setLayoutManager(linearLayoutManager);
        //Make recyclerview show rows from the bottom
        linearLayoutManager.setStackFromEnd(true);
        chatRv.setAdapter(chatAdapter);

        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messageArrayList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Message message = snapshot.getValue(Message.class);

                    //If receiverID is same as currentuserID
                    if(message.receiverID.equals(receiverId) &&
                            message.senderID.equals(firebaseAuth.getUid())||
                    message.receiverID.equals(firebaseAuth.getUid()) &&
                    message.senderID.equals(receiverId)) {
                        messageArrayList.add(message);
                    }
                }
                chatAdapter.notifyDataSetChanged();
                chatRv.smoothScrollToPosition(chatRv.getAdapter().getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = chatMessage.getText().toString();
                if(message.isEmpty())
                {
                    Toast.makeText(ChatActivity.this,"Please enter a message",Toast.LENGTH_SHORT);
                    return;
                }
                chatMessage.setText("");
                Date date = new Date();
                Message messages = new Message(message,senderID,receiverId,date.getTime());

                database.getReference().child("chats")
                        .push()
                        .setValue(messages);

            }
        });
    }
}
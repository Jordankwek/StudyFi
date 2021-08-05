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
    //Receiver and sender info
    String receiverName, receiverId, senderID;
    //Send button
    CardView sendBtn;
    //User message input
    EditText chatMessage;
    //Access realtime database
    FirebaseDatabase database;
    //Get user authentication details
    FirebaseAuth firebaseAuth;

    //Chat adapter for the recyclerview
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();

        //Initialize arraylist to store messages
        ArrayList<Message> messageArrayList = new ArrayList<>();

        //Accessing database
        database = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //Initialize Firebase auth
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

        //reference to chats in firebase
        DatabaseReference chatReference = database.getReference().child("chats");

        //Messaging chats recyclerview
        RecyclerView chatRv = findViewById(R.id.chatRv);
        chatAdapter = new ChatAdapter(ChatActivity.this, messageArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chatRv.setLayoutManager(linearLayoutManager);
        //Make recyclerview show rows from the bottom
        linearLayoutManager.setStackFromEnd(false);
        chatRv.setAdapter(chatAdapter);

        //When changes is made to the chat in the firebase
        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageArrayList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Message message = snapshot.getValue(Message.class);

                    //Checks if the receiver and sender info matches
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

        //When user sends a message
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

    //Prevent activity from stacking
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
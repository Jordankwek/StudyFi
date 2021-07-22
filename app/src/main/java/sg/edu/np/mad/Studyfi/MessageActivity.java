package sg.edu.np.mad.Studyfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<User> userList;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        getSupportActionBar().hide();
        //To access firebase
        auth = FirebaseAuth.getInstance();
        //To access database
        database = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/");
        userList = new ArrayList<>();
        //To reading or writing of data
        DatabaseReference reference = database.getReference().child("Users");

        //receive events about data changes to user
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapShot: dataSnapshot.getChildren())
                {
                    User user = snapShot.getValue(User.class);
                    userList.add(user);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(auth.getCurrentUser()==null)
        {
            Intent intent = new Intent(MessageActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }

        RecyclerView messagerv = findViewById(R.id.chatrv);
        messageAdapter = new MessageAdapter(userList,MessageActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messagerv.setLayoutManager(linearLayoutManager);
        messagerv.setAdapter(messageAdapter);

    }
}
package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    TextView receiverName;
    String name, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();

        name = getIntent().getStringExtra("name");
        uid = getIntent().getStringExtra("uid");

        receiverName = findViewById(R.id.receiverName);
        receiverName.setText(name);
    }
}
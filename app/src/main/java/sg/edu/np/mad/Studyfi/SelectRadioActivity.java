package sg.edu.np.mad.Studyfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectRadioActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<Radio> radioList = new ArrayList<>();
    RadioAdapter radioAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_radio);
        getSupportActionBar().hide();


        //To access firebase
        auth = FirebaseAuth.getInstance();
        //To access database
        database = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/");

        //To reading or writing of data
        DatabaseReference reference = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Radio");
        //receive events about data changes to user
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapShot: dataSnapshot.getChildren())
                {
                    Radio radio = new Radio();
                    radio.setRadioLink(snapShot.child("Link").getValue().toString());
                    radio.setOffline(Boolean.valueOf(snapShot.child("isOffline").getValue().toString()));
                    radio.setRadioName(snapShot.child("Name").getValue().toString());
                    radioList.add(radio);
                    //Radio radio = snapShot.getValue(Radio.class);
                    Log.d("Dead",snapShot.toString());
                    //Remove current user from the recyclerview
                    //radioList.add(radio);
                }
                radioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*

        //Create radio items
        Radio radio1 = new Radio();
        radio1.radioLink = "https://playerservices.streamtheworld.com/api/livestream-redirect/KISS_92.mp3";
        radio1.radioName = "Kiss 92";
        radio1.isOffline = false;
        //radio1.radioPic = findViewById(R.drawable.ic_launcher_background);

        Radio radio2 = new Radio();
        radio2.radioLink = "https://listen.181fm.com/181-xmix_128k.mp3";
        radio2.radioName = "181.FM";
        radio2.isOffline = false;

        Radio radio3 = new Radio();
        radio3.radioLink = "https://playerservices.streamtheworld.com/api/livestream-redirect/SYMPHONY924.mp3";
        radio3.radioName = "Symphony FM 92.4";
        radio3.isOffline = false;

        Radio radio4 = new Radio();
        radio4.radioLink = "https://jazzlounge.ice.infomaniak.ch/jazzlounge-high.mp3";
        radio4.radioName = "Jazz Radio - Lounge";
        radio4.isOffline = false;

        Radio radio5 = new Radio();
        radio5.radioLink = "https://radioclassique.ice.infomaniak.ch/radioclassique-high.mp3";
        radio5.radioName = "Radio Classique";
        radio5.isOffline = false;
*/
        Radio radioOffline1 = new Radio();
        radioOffline1.radioLink = "rainsound";
        radioOffline1.radioName = "Heavy Rain";
        radioOffline1.isOffline = true;

        Radio radioOffline2 = new Radio();
        radioOffline2.radioLink = "forestchill";
        radioOffline2.radioName = "Forest Chill";
        radioOffline2.isOffline = true;

        Radio radioOffline3 = new Radio();
        radioOffline3.radioLink = "relaxingbell";
        radioOffline3.radioName = "Soothing Bell";
        radioOffline3.isOffline = true;

        radioList.add(radioOffline1);
        radioList.add(radioOffline2);
        radioList.add(radioOffline3);

        RecyclerView selectradiorv = findViewById(R.id.radiorv);
        radioAdapter = new RadioAdapter(radioList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        selectradiorv.setLayoutManager(linearLayoutManager);
        selectradiorv.setAdapter(radioAdapter);

    }
}
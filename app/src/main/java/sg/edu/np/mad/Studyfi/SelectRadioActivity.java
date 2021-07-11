package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class SelectRadioActivity extends AppCompatActivity {


    ArrayList<Radio> radioList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_radio);
        getSupportActionBar().hide();
        ImageView backBtn = findViewById(R.id.radioSelectBack);

        Radio radio1 = new Radio();
        radio1.radioID = 1;
        radio1.radioLink = "https://playerservices.streamtheworld.com/api/livestream-redirect/KISS_92.mp3";
        radio1.radioName = "Kiss 92";
        //radio1.radioPic = findViewById(R.drawable.ic_launcher_background);

        Radio radio2 = new Radio();
        radio2.radioID = 2;
        radio2.radioLink = "https://listen.181fm.com/181-xmix_128k.mp3";
        radio2.radioName = "181.FM";

        Radio radio3 = new Radio();
        radio3.radioID = 3;
        radio3.radioLink = "https://playerservices.streamtheworld.com/api/livestream-redirect/SYMPHONY924.mp3";
        radio3.radioName = "Symphony FM 92.4";

        Radio radio4 = new Radio();
        radio4.radioID = 4;
        radio4.radioLink = "https://jazzlounge.ice.infomaniak.ch/jazzlounge-high.mp3";
        radio4.radioName = "Jazz Radio - Lounge";

        Radio radio5 = new Radio();
        radio5.radioID = 5;
        radio5.radioLink = "https://tuner.m1.fm/chillout.mp3;";
        radio5.radioName = "Chillout";

        Radio radio6 = new Radio();
        radio6.radioID = 6;
        radio6.radioLink = "https://radioclassique.ice.infomaniak.ch/radioclassique-high.mp3";
        radio6.radioName = "Radio Classique";

        radioList.add(radio1);
        radioList.add(radio2);
        radioList.add(radio3);
        radioList.add(radio4);
        radioList.add(radio5);
        radioList.add(radio6);

        RecyclerView selectradiorv = findViewById(R.id.radiorv);
        RadioAdapter radioAdapter = new RadioAdapter(radioList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        selectradiorv.setLayoutManager(linearLayoutManager);
        selectradiorv.setAdapter(radioAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectRadioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
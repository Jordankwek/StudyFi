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

        Radio radioOffline1 = new Radio();
        radioOffline1.radioLink = "rainsound";
        radioOffline1.radioName = "Heavy Rain sounds";
        radioOffline1.isOffline = true;

        radioList.add(radio1);
        radioList.add(radio2);
        radioList.add(radio3);
        radioList.add(radio4);
        radioList.add(radio5);
        radioList.add(radioOffline1);

        RecyclerView selectradiorv = findViewById(R.id.radiorv);
        RadioAdapter radioAdapter = new RadioAdapter(radioList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        selectradiorv.setLayoutManager(linearLayoutManager);
        selectradiorv.setAdapter(radioAdapter);

    }
}
package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class RadioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        getSupportActionBar().hide();

        ImageButton pausebutton = findViewById(R.id.pauseRadio);
        ImageButton playbutton = findViewById(R.id.playRadio);
        ImageView radioPicture = findViewById(R.id.radioImage);
        TextView radioTitle = findViewById(R.id.radioTitle);
        MediaPlayer mediaPlayer = new MediaPlayer();
        Intent receivingData = getIntent();
        String radioLink = receivingData.getStringExtra("Link");
        String radioName = receivingData.getStringExtra("Title");
        radioTitle.setText(radioName);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = radioLink; // your URL here
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                pausebutton.setEnabled(true);
                playbutton.setEnabled(false);

                try {
                    mediaPlayer.setDataSource(url);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.d("1","first");
                }
                catch (IllegalStateException e){
                    e.printStackTrace();
                }

                //Reason for separating them into 2 tries is to know which is causing the error
                //Buffering
                try {
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.d("2","Second");
                }
                catch (IllegalStateException e){
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });


            }
        });

        //Pause / stop button
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                playbutton.setEnabled(true);
                pausebutton.setEnabled(false);
            }
        });






    }


}
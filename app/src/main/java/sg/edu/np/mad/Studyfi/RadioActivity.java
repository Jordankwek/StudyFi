package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public class RadioActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        getSupportActionBar().hide();

        ImageButton pausebutton = findViewById(R.id.pauseRadio);
        ImageButton playbutton = findViewById(R.id.playRadio);
        ImageView radioPicture = findViewById(R.id.radioImage);
        TextView radioTitle = findViewById(R.id.radioTitle);
        mediaPlayer = new MediaPlayer();

        //Receive intent data
        Intent receivingData = getIntent();
        String radioLink = receivingData.getStringExtra("Link");
        String radioName = receivingData.getStringExtra("Title");
        Boolean radioInternet = receivingData.getBooleanExtra("IsOffline", false);
        radioTitle.setText(radioName);

        //Plays radio when clicked
        if (radioInternet == false) {
            playbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = radioLink; // your URL here
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    pausebutton.setEnabled(true);
                    playbutton.setEnabled(false);

                    try {
                        mediaPlayer.setDataSource(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }

                    //Reason for separating them into 2 tries is to know which is causing the error
                    //Buffering
                    try {
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
            });
        }
        else{
            playbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(radioLink.equals("rainsound")){
                        pausebutton.setEnabled(true);
                        playbutton.setEnabled(false);
                        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.rainsound);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mediaPlayer.start();
                            }
                        });
                    }
                }
            });

        }

        //Stop button
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                playbutton.setEnabled(true);
                pausebutton.setEnabled(false);
            }
        });


    }

    //When user goes exits radio activity page, radio stops. Will find a way to improve this feature
    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.stop();
    }
    //Will implement more features to allow users to
    // use app while listening to songs at the same time


}
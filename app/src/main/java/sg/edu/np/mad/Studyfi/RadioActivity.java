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

    private static MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        getSupportActionBar().hide();

        ImageButton pausebutton = findViewById(R.id.pauseRadio);
        ImageButton playbutton = findViewById(R.id.playRadio);
        TextView radioTitle = findViewById(R.id.radioTitle);
        TextView reference = findViewById(R.id.reference);

        //Receive intent data
        Intent receivingData = getIntent();
        String radioLink = receivingData.getStringExtra("Link");
        String radioName = receivingData.getStringExtra("Title");
        Boolean radioInternet = receivingData.getBooleanExtra("IsOffline", false);
        reference.setText("Radio: " + radioLink);
        radioTitle.setText(radioName);
        mediaPlayer = new MediaPlayer();
        /*
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
         */

        //Plays online radio when clicked
        if (radioInternet == false) {
            /*
            if(mediaPlayer != null)
            {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
             */
            playbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RadioActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
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

        //When offline song is clicked
        else{
            reference.setText("Music: https://www.bensound.com");
            playbutton.setOnClickListener(new View.OnClickListener() {
                int link;
                @Override
                public void onClick(View v) {
                    if(radioLink.equals("lovejazz")){
                        link = R.raw.lovejazz;
                    }
                    else if(radioLink.equals("memories"))
                    {
                        link = R.raw.memories;
                    }
                    else if(radioLink.equals("relaxing"))
                    {
                        link = R.raw.relaxing;
                    }
                    else if(radioLink.equals("romantic"))
                    {
                        link = R.raw.romantic;
                    }
                    pausebutton.setEnabled(true);
                    playbutton.setEnabled(false);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),link);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
            });

        }

        //Stop button
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
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
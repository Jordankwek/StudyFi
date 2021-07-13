package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;

public class TimerActivity extends AppCompatActivity {

    long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //Connect activity_timer items to TimerActivity
        TextView timer = findViewById(R.id.timer);
        ImageView startAndPauseButton = findViewById(R.id.startAndPauseButton);
        EditText setTimerInput = findViewById(R.id.setTimerInput);
        setTimerInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button setTimerConfirm = findViewById(R.id.setTimerConfirm);
        Button resetTimer = findViewById(R.id.resetTimer);

        //Create new Timer Object
        Timer timerObj = new Timer();

        //Toggle Start and Pause button to switch between Paused and Resumed
        startAndPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerObj.paused) {
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                    timerObj.paused = false;

                    //Starts counting down again
                    countDown((timeLeft / 1000), timer, startAndPauseButton, timerObj);
                }
                else {
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                    timerObj.paused = true;

                    myCountDown.cancel();
                }
            }
        });

        //Button to confirm the Timer input
        setTimerConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerObj.paused) {
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                    timerObj.paused = false;
                }

                Editable timeAmount = setTimerInput.getText();
                String timeAmountString = timeAmount.toString();
                Long timeAmountLong = Long.parseLong(timeAmountString);

                timer.setText("00 : 00");
                setTimerInput.setText("");

                //Starts counting down
                countDown(timeAmountLong, timer, startAndPauseButton, timerObj);
            }
        });

        //Button to reset the Timer and Timer input
        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setText("00 : 00");
                startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                timerObj.paused = true;
                setTimerInput.setText("");

                myCountDown.cancel();
            }
        });
    }

    CountDownTimer myCountDown;

    private void countDown(long time, TextView timer, ImageView startAndPauseButton, Timer timerObj){
        myCountDown = new CountDownTimer((60000 * time), 1000){
            public void onTick(long millisUntilFinished){
                timeLeft = millisUntilFinished;

                if (millisUntilFinished > 60000) {
                    long seconds = millisUntilFinished % 60000;
                    long minutes = (millisUntilFinished - seconds) / 60000;
                    if (minutes < 10) {

                    }
                    timer.setText(minutes + " : " + seconds);
                }
                else {
                    long seconds = millisUntilFinished % 60000;
                    timer.setText("00 : " + seconds);
                }
            }

            public void onFinish(){
                timer.setText("00 : 00");
                startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                timerObj.paused = true;
                myCountDown.cancel();
            }
        };
        myCountDown.start();
    }
}
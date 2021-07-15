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

    long secondsLeft;
    long minutesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        getSupportActionBar().hide();
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
                    countDown(secondsLeft, minutesLeft, timer, startAndPauseButton, timerObj);
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
                startAndPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                timerObj.paused = false;

                Editable timeAmount = setTimerInput.getText();
                String timeAmountString = timeAmount.toString();
                Long minuteInput = Long.parseLong(timeAmountString);

                timer.setText("00 : 00");
                setTimerInput.setText("");

                //Starts counting down
                countDown(0, minuteInput, timer, startAndPauseButton, timerObj);
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

    private void countDown(long secondInput, long minuteInput, TextView timer, ImageView startAndPauseButton, Timer timerObj){
        myCountDown = new CountDownTimer((1000 * secondInput + (60000 * minuteInput)), 1000){
            public void onTick(long millisUntilFinished){
                long seconds = (millisUntilFinished % 60000) / 1000;
                long minutes = (millisUntilFinished - seconds) / 60000;

                secondsLeft = seconds;
                minutesLeft = minutes;

                String secondsString = Long.toString(seconds);
                if (seconds < 10) {
                    secondsString = "0" + secondsString;
                }

                String minutesString = Long.toString(minutes);
                if (minutes < 10) {
                    minutesString = "0" + minutesString;
                }

                if (millisUntilFinished > 60000) {
                    timer.setText(minutesString + " : " + secondsString);
                }
                else {
                    timer.setText("00 : " + secondsString);
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
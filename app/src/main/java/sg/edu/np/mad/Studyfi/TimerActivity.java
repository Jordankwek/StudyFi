package sg.edu.np.mad.Studyfi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;

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
        EditText setTimerMin = findViewById(R.id.setTimerInputMin);
        EditText setTimerSec = findViewById(R.id.setTimerInputSec);
        setTimerMin.setInputType(InputType.TYPE_CLASS_NUMBER);
        setTimerSec.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button setTimerConfirm = findViewById(R.id.setTimerConfirm);
        Button resetTimer = findViewById(R.id.resetTimer);

        //Create new Timer Object
        Timer timerObj = new Timer();
        startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
        timerObj.paused = true;
        timerObj.state = "Inactive";

        //Toggle Start and Pause button to switch between Paused and Resumed
        startAndPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerObj.state == "Countdown") {
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
                else if (timerObj.state == "Stopwatch") {
                    if (timerObj.paused) {
                        startAndPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                        timerObj.paused = false;

                        //Starts counting down again
                        countUp(timerObj.amountOfTimeStored, timer, startAndPauseButton, timerObj);
                    }
                    else {
                        startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                        timerObj.paused = true;

                        myCountUp.cancel();
                    }
                }
                else {
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                    timerObj.paused = false;
                    timerObj.state = "Stopwatch";
                    // Start counting up
                    countUp(timerObj.amountOfTimeStored, timer, startAndPauseButton, timerObj);
                }
            }
        });

        //Button to confirm the Timer input
        setTimerConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timerObj.state != "Inactive") {
                    if (timerObj.state == "Countdown" && !timerObj.paused) {
                        myCountDown.cancel();
                    }
                    else if (timerObj.state == "Stopwatch" && !timerObj.paused) {
                        myCountUp.cancel();
                    }

                    timer.setText("00 : 00");
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                    timerObj.amountOfTimeStored = 0;
                    timerObj.paused = true;
                    timerObj.state = "Inactive";
                    setTimerMin.setText("");
                }


                Editable timeAmountMin = setTimerMin.getText();
                Editable timeAmountSec = setTimerSec.getText();
                String timeAmountStringMin = timeAmountMin.toString();
                String timeAmountStringSec = timeAmountSec.toString();
                if(timeAmountStringMin.isEmpty() && timeAmountStringSec.isEmpty())
                {
                    Toast.makeText(TimerActivity.this, "Please enter time", Toast.LENGTH_SHORT).show();

                }

                else {
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                    timerObj.paused = false;
                    timerObj.state = "Countdown";
                    Long minuteInput;
                    Long secondInput;
                    if(timeAmountStringMin.isEmpty())
                    {
                        minuteInput = 0L;
                    }
                    else {
                        minuteInput = Long.parseLong(timeAmountStringMin);
                    }
                    if(timeAmountStringSec.isEmpty())
                    {
                        secondInput = 0L;
                    }
                    else {
                        secondInput = Long.parseLong(timeAmountStringSec);

                    }
                    timer.setText("00 : 00");
                    setTimerMin.setText("");
                    setTimerSec.setText("");

                    //Starts counting down
                    countDown(secondInput + 1, minuteInput, timer, startAndPauseButton, timerObj);
                }

            }
        });

        //Button to reset the Timer and Timer input
        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerObj.state != "Inactive") {
                    if (timerObj.state == "Countdown" && !timerObj.paused) {
                        myCountDown.cancel();
                    }
                    else if (timerObj.state == "Stopwatch" && !timerObj.paused) {
                        myCountUp.cancel();
                    }

                    timer.setText("00 : 00");
                    startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                    timerObj.amountOfTimeStored = 0;
                    timerObj.paused = true;
                    timerObj.state = "Inactive";
                    setTimerMin.setText("");
                }
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
                timerObj.state = "Inactive";
                myCountDown.cancel();

                // Send toast
                Toast.makeText(getApplicationContext(), "Timer Done", Toast.LENGTH_SHORT).show();
            }
        };
        myCountDown.start();
    }

    CountDownTimer myCountUp;
    private void countUp(long timePassed, TextView timer, ImageView startAndPauseButton, Timer timerObj){
        myCountUp = new CountDownTimer(5999000, 1000){
            public void onTick(long millisUntilFinished) {
                long seconds = (5999000 - millisUntilFinished + timePassed) / 1000;
                timerObj.amountOfTimeStored = seconds * 1000;

                // Convert the seconds passed into minutes and seconds
                if (seconds < 60) {
                    String secondsString = Long.toString(seconds);
                    if (seconds < 10) {
                        secondsString = "0" + secondsString;
                    }

                    timer.setText("00 : " + secondsString);
                }
                else {
                    long secondsLeft = seconds % 60;
                    long minutes = (seconds - secondsLeft) / 60;
                    String secondsString = Long.toString(secondsLeft);
                    String minutesString = Long.toString(minutes);

                    if (secondsLeft < 10) {
                        secondsString = "0" + secondsString;
                    }

                    if (minutes < 10) {
                        minutesString = "0" + minutesString;
                    }

                    timer.setText(minutesString + " : " + secondsString);
                }

                //onTick(seconds);
            }

            public void onFinish() {
                timer.setText("00 : 00");
                startAndPauseButton.setImageResource(android.R.drawable.ic_media_play);
                timerObj.paused = true;
                myCountDown.cancel();
            }
        };
        myCountUp.start();
    }

}
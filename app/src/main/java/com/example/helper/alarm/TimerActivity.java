package com.example.helper.alarm;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TimePicker;

import com.example.helper.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity implements AlarmInterface {

    TimePicker timePicker;
    TextClock textClock;
    Timer timer;
    Ringtone ringtone;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timePicker = findViewById(R.id.timePicker);
        textClock = findViewById(R.id.textClock);
        stopButton = findViewById(R.id.stopButton);
        timer = new Timer();
        ringtone = RingtoneManager.getRingtone(this,
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("Debug", "waiting . . . ");
                if (textClock.getText().toString().equals(alertTime())) {
                    ringtone.play();
                } else {
                    ringtone.stop();
                }
            }
        }, 0, 1000);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
            }
        });

    }

    @Override
    public void setAlarm(String time) {

    }

    @Override
    public String alertTime() {
        int alarmHour = timePicker.getHour();
        int alarmMin = timePicker.getMinute();

        String stringAlarmTime;
        String stringAlarmMin;

        if (alarmMin < 10) {
            stringAlarmMin = "0" + alarmMin;
        } else {
            stringAlarmMin = "" + alarmMin;
        }

        if (alarmHour > 12) {
            alarmHour -= 12;
            stringAlarmTime = alarmHour + ":" + stringAlarmMin + " PM";
        } else {
            stringAlarmTime = alarmHour + ":" + stringAlarmMin + " AM";
        }

        return stringAlarmTime;
    }
}

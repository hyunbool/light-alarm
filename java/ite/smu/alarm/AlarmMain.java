package ite.smu.alarm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

/**
 * Created by glori on 2017-12-06.
 */

public class AlarmMain extends AppCompatActivity{

    static boolean nservice;
    ImageButton newsBtn, weatherBtn;
    ImageButton soundBtn, vibeBtn, lightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_main);

        newsBtn = (ImageButton)findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(getApplicationContext(),News.class);
                startActivity(newsIntent);
            }
        });


        weatherBtn = (ImageButton)findViewById(R.id.weatherBtn);
        weatherBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent weaIntent = new Intent(getApplicationContext(),Weather.class);
                startActivity(weaIntent);
            }
        });

        //서비스 종료
        soundBtn = (ImageButton) findViewById(R.id.stopbtn);
        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(AlarmMain.this, AlarmSoundService.class));
                nservice = false;
            }
        });

        //서비스 종료
        vibeBtn = (ImageButton) findViewById(R.id.vib_stop_btn);
        vibeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(AlarmMain.this, AlarmVibe.class));
                nservice = false;
            }
        });

        //서비스 종료
        lightBtn = (ImageButton) findViewById(R.id.light_stop_btn);
        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(AlarmMain.this, AlarmLight.class));
                nservice = false;
            }
        });
    }

    public void onClick(View view){
        finish();
        stopService(new Intent(AlarmMain.this, AlarmSoundService.class));
        stopService(new Intent(AlarmMain.this, AlarmVibe.class));
        stopService(new Intent(AlarmMain.this, AlarmLight.class));
    }
}

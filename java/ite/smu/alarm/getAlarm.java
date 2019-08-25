package ite.smu.alarm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

import static ite.smu.alarm.MainActivity.RESULT;

/**
 * Created by glori on 2017-12-03.
 */

public class getAlarm extends AppCompatActivity {

    TimePicker timePicker;

    String time = "";
    String day = "";
    CheckBox mon, tue, wed, thu, fri, sat, sun;
    SwitchCompat light, sound, vib;
    String monday = "";
    Boolean mon_on, tue_on, wed_on, thu_on, fri_on, sat_on, sun_on  = false;
    String tuesday = "";
    String wednesday = "";
    String thursday = "";
    String friday = "";
    String saturday = "";
    String sunday = "";
    boolean light_img, sound_img, vib_img;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        final Intent data = new Intent();

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                if(i1<10)
                    time = i+":0"+i1;
                else
                    time = i+":"+i1;

                if(i<12)
                    day = "AM";
                else
                    day = "PM";

                data.putExtra("time", time);
                data.putExtra("day", day);
                data.putExtra("hour", i);
                data.putExtra("minute", i1);

            }
        });

        mon = (CheckBox)findViewById(R.id.monday);
        tue = (CheckBox)findViewById(R.id.tuesday);
        wed = (CheckBox)findViewById(R.id.wednesday);
        thu = (CheckBox)findViewById(R.id.thursday);
        fri = (CheckBox)findViewById(R.id.friday);
        sat = (CheckBox)findViewById(R.id.saturday);
        sun = (CheckBox)findViewById(R.id.sunday);

        light = (SwitchCompat)findViewById(R.id.light);
        sound = (SwitchCompat)findViewById(R.id.sound);
        vib = (SwitchCompat)findViewById(R.id.vib);


        mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(mon.isChecked()) {
                    monday += "월 ";
                    mon_on = true;
                }
                else
                    monday = "";

                data.putExtra("monday", monday);
                data.putExtra("mon_on", mon_on);

            }
        });
        tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tue.isChecked()) {
                    tuesday += "화 ";
                    tue_on = true;
                }
                else
                    tuesday = "";


                data.putExtra("tuesday", tuesday);
                data.putExtra("tue_on", tue_on);
            }
        });

        wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(wed.isChecked()) {
                    wednesday += "수 ";
                    wed_on = true;
                }
                else
                    wednesday = "";


                data.putExtra("wednesday", wednesday);
                data.putExtra("wed_on", wed_on);

            }
        });
        thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(thu.isChecked()) {
                    thursday += "목 ";
                    thu_on = true;
                }
                else
                    thursday = "";

                data.putExtra("thu_on", thu_on);
                data.putExtra("thursday", thursday);
            }
        });

        fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(fri.isChecked()) {
                    friday += "금 ";
                    fri_on = true;
                }
                else
                    friday = "";

                data.putExtra("fri_on", fri_on);
                data.putExtra("friday", friday);

            }
        });
        sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sat.isChecked()) {
                    saturday += "토 ";
                    sat_on = true;
                }
                else
                    saturday = "";

                data.putExtra("sat_on", sat_on);
                data.putExtra("saturday", saturday);
            }
        });

        sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sun.isChecked()) {
                    sunday += "일 ";
                    sun_on = true;
                }
                else
                    sunday = "";

                data.putExtra("sun_on", sun_on);
                data.putExtra("sunday", sunday);

            }
        });

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(light.isChecked())
                    light_img = true;
                else
                    light_img = false;

                data.putExtra("light_on_off", light_img);

            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sound.isChecked())
                    sound_img = true;
                else
                    sound_img = false;

                data.putExtra("sound_on_off", sound_img);

            }
        });

        vib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(vib.isChecked())
                    vib_img = true;
                else
                    vib_img = false;

                data.putExtra("vib_on_off", vib_img);

            }
        });

        setResult(RESULT, data);

    }


    public void onClick(View view){
        finish();
    }
}

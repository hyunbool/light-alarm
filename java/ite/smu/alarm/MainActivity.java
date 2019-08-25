package ite.smu.alarm;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements ListViewAdapter.ListBtnClickListener{

    static boolean nsound, nlight, nvib; // 리시버에서 변수 받아 소리, 빛, 진동 여부에 따라 서비스 시작할 것인지 말것인지 정함
    private Intent intent; // 리시버로 보내기 위한 인텐트
    private PendingIntent ServicePending; // 인텐트를 다른 어플리케이션에서 실행할 수 있도록 권한을 주는 것
    private AlarmManager AM; // 알람매니저 객체

    public ListView listView; // 알람 리스트뷰
    ListViewAdapter adapter;
    ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();

    public static final int RESULT = 0; // getAlarm()에서 데이터 받아오는데 필요한 requestCode

    int id; //알람 실행 시 어떤 알람을 실행시킬 것인지 리스트뷰의 포지션을 받는 변수

    ArrayList hour, minute; // 시간 리스트
    ArrayList sound_on, light_on, vib_on; // 소리, 빛, 진동 리스트
    ArrayList mon_on, tue_on, wed_on, thu_on, fri_on, sat_on, sun_on; // 요일 리스트
    String day, time, mon, tue, wed, thu, fri, sat, sun = ""; //리스트뷰의 아이템에 요일을 표시하기 위한 스트링 변수들
    Boolean light_img, sound_img, vib_img = true; // 리스트뷰의 아이템에 그림을 표시하기 위한 불린 변수들

    Calendar mCalendar; // 알람을 울릴 시간을 정하는 캘린더 객체


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadItemsFromDB(items) ;

        adapter = new ListViewAdapter(this, R.layout.list_item, items, this);

        listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        hour = new ArrayList();
        minute = new ArrayList();

        sound_on = new ArrayList();
        light_on = new ArrayList();
        vib_on = new ArrayList();

        mon_on = new ArrayList();
        tue_on = new ArrayList();
        wed_on = new ArrayList();
        thu_on = new ArrayList();
        fri_on = new ArrayList();
        sat_on = new ArrayList();
        sun_on = new ArrayList();
        AM = (AlarmManager) getSystemService(ALARM_SERVICE);

    }


    @Override
    public void onListBtnClick(View v){
        switch(v.getId()){
            case R.id.confirm:

                id = listView.getPositionForView(v);
                int nhour = Integer.parseInt(hour.get(id).toString());
                int nmin = Integer.parseInt(minute.get(id).toString());

                nsound = Boolean.parseBoolean(sound_on.get(id).toString());
                nlight = Boolean.parseBoolean(light_on.get(id).toString()); // 빛 여부
                nvib = Boolean.parseBoolean(vib_on.get(id).toString()); // 진동 여부

                boolean nmon = Boolean.parseBoolean(mon_on.get(id).toString());
                boolean ntue = Boolean.parseBoolean(tue_on.get(id).toString());
                boolean nwed = Boolean.parseBoolean(wed_on.get(id).toString());
                boolean nthu = Boolean.parseBoolean(thu_on.get(id).toString());
                boolean nfri = Boolean.parseBoolean(fri_on.get(id).toString());
                boolean nsat = Boolean.parseBoolean(sat_on.get(id).toString());
                boolean nsun = Boolean.parseBoolean(sun_on.get(id).toString());


                mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.HOUR_OF_DAY, nhour);
                mCalendar.set(Calendar.MINUTE, nmin);
                mCalendar.set(Calendar.SECOND, 0);

                //Receiver로 보내기 위한 인텐트
                intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                ServicePending = PendingIntent.getBroadcast(MainActivity.this, id, intent, 0);

                if(nmon = true){
                    createAlarm(2);
                }if(ntue = true){
                    createAlarm(3);
                }
                if(nwed = true){
                    createAlarm(4);
                }
                if(nthu = true){
                    createAlarm(5);
                }
                if(nfri = true){
                    createAlarm(6);
                }
                if(nsat = true){
                    createAlarm(7);
                }
                if(nsun = true){
                    createAlarm(0);
                }
                if(nmon & ntue& nwed & nthu == false && nfri == false && nsat == false && nsun == false)
                    createAlarm_one();

                Toast.makeText(getBaseContext(), nhour+":"+nmin+" 알람 설정", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                id = listView.getPositionForView(v);
                ServicePending = PendingIntent.getBroadcast(MainActivity.this, id, intent, 0);
                AM.cancel(ServicePending);
                Toast.makeText(getBaseContext(), id+" canceled", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void createAlarm(int week){
        mCalendar.set(Calendar.HOUR_OF_DAY, week);
        AM.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), 1000*60*60*24, ServicePending);
    }

    public void createAlarm_one(){
        AM.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), ServicePending);
    }



    public boolean onCreateOptionsMenu(Menu menu) { // inflater함수를 이용해서 menu 리소스를 menu로 변환.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.setting_btn:
                Intent intent = new Intent(getApplicationContext(), getAlarm.class);
                startActivityForResult(intent, RESULT);

                break;
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Drawable light, sound, vib;


        hour.add(data.getExtras().getInt("hour"));
        minute.add(data.getExtras().getInt("minute"));

        time = (data.getExtras().getString("time"));

        day = data.getExtras().getString("day");

        mon = data.getExtras().getString("monday");
        mon_on.add(data.getExtras().getBoolean("mon_on"));
        tue = data.getExtras().getString("tuesday");
        tue_on.add(data.getExtras().getBoolean("tue_on"));
        wed = data.getExtras().getString("wednesday");
        wed_on.add(data.getExtras().getBoolean("wed_on"));
        thu = data.getExtras().getString("thursday");
        thu_on.add(data.getExtras().getBoolean("thu_on"));
        fri = data.getExtras().getString("friday");
        fri_on.add(data.getExtras().getBoolean("fri_on"));
        sat = data.getExtras().getString("saturday");
        sat_on.add(data.getExtras().getBoolean("sat_on"));
        sun = data.getExtras().getString("sunday");
        sun_on.add(data.getExtras().getBoolean("sun_on"));

        light_img = data.getExtras().getBoolean("light_on_off");
        light_on.add(light_img);
        sound_img = data.getExtras().getBoolean("sound_on_off");
        sound_on.add(sound_img);
        vib_img = data.getExtras().getBoolean("vib_on_off");
        vib_on.add(vib_img);

        if(light_img)
            light = getResources().getDrawable(R.drawable.light_on);
        else
            light = getResources().getDrawable(R.drawable.light_off);

        if(sound_img)
            sound = getResources().getDrawable(R.drawable.sound_on);
        else
            sound = getResources().getDrawable(R.drawable.sound_off);

        if(vib_img)
            vib = getResources().getDrawable(R.drawable.vib_on);
        else
            vib = getResources().getDrawable(R.drawable.vib_off);


        adapter.addItem(light, sound, vib, day, time, mon, tue, wed, thu, fri, sat, sun);
        adapter.notifyDataSetChanged();
    }

    public boolean loadItemsFromDB(ArrayList<ListViewItem> list) {
        ListViewItem item;
        int i;

        if (list == null) {
            list = new ArrayList<ListViewItem>();
        }

        return true;
    }
}
